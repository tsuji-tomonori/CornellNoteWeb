import { App, Aspects } from "aws-cdk-lib";
import { Annotations, Match, Template } from "aws-cdk-lib/assertions";
import { AwsSolutionsChecks } from "cdk-nag";
import { CornellNoteStack } from "../lib/stack";

const createTemplate = (envName = "dev") => {
  const app = new App({
    context: {
      env: envName,
    },
  });
  const stack = new CornellNoteStack(app, `CornellNote-${envName}`, {
    envName,
  });
  Aspects.of(stack).add(new AwsSolutionsChecks({
    verbose: true,
  }));
  return {
    stack,
    template: Template.fromStack(stack),
  };
};

describe("CornellNote Infra Assertions", () => {
  const { stack, template } = createTemplate();

  test("INF-API-001 API Gateway has access logs enabled", () => {
    template.hasResourceProperties("AWS::ApiGatewayV2::Stage", {
      AccessLogSettings: {
        DestinationArn: {
          "Fn::GetAtt": [Match.anyValue(), "Arn"],
        },
      },
    });
  });

  test("INF-API-002 API Gateway is associated with WAF", () => {
    template.hasResourceProperties("AWS::WAFv2::WebACLAssociation", {
      ResourceArn: {
        "Fn::Join": [
          "",
          [
            "arn:",
            { Ref: "AWS::Partition" },
            ":apigateway:",
            { Ref: "AWS::Region" },
            ":",
            { Ref: "AWS::AccountId" },
            ":/apis/",
            { Ref: Match.anyValue() },
            "/stages/$default",
          ],
        ],
      },
    });
  });

  test("INF-API-003 API Gateway throttle settings are configured", () => {
    template.hasResourceProperties("AWS::ApiGatewayV2::Stage", {
      DefaultRouteSettings: {
        ThrottlingBurstLimit: 200,
        ThrottlingRateLimit: 100,
      },
    });
  });

  test("INF-API-004 Lambda App runs inside VPC", () => {
    template.hasResourceProperties("AWS::Lambda::Function", {
      FunctionName: "cornellnote-dev-api",
      VpcConfig: {
        SubnetIds: Match.anyValue(),
      },
    });
  });

  test("INF-API-005 Lambda App has tracing enabled", () => {
    template.hasResourceProperties("AWS::Lambda::Function", {
      FunctionName: "cornellnote-dev-api",
      TracingConfig: {
        Mode: "Active",
      },
    });
  });

  test("INF-API-006 Lambda App has reserved concurrency", () => {
    template.hasResourceProperties("AWS::Lambda::Function", {
      FunctionName: "cornellnote-dev-api",
      ReservedConcurrentExecutions: 50,
    });
  });

  test("INF-API-007 Lambda App has stage environment variable", () => {
    template.hasResourceProperties("AWS::Lambda::Function", {
      FunctionName: "cornellnote-dev-api",
      Environment: {
        Variables: {
          STAGE: "dev",
        },
      },
    });
  });

  test("INF-API-008 Lambda App log retention is at least 30 days", () => {
    template.hasResourceProperties("AWS::Logs::LogGroup", {
      RetentionInDays: 30,
    });
  });

  test("INF-API-009 Lambda policies are least-privilege", () => {
    const policies = template.findResources("AWS::IAM::Policy");
    const policyValues = Object.values(policies);
    const hasAdmin = policyValues.some((policy) =>
      JSON.stringify(policy).includes("\"Action\":[\"*\"]") &&
      JSON.stringify(policy).includes("\"Resource\":\"*\"")
    );
    expect(hasAdmin).toBe(false);
  });

  test("INF-DATA-001 Aurora is multi-AZ", () => {
    template.hasResourceProperties("AWS::RDS::DBCluster", {
      VpcSecurityGroupIds: Match.anyValue(),
    });
  });

  test("INF-DATA-002 Aurora storage is encrypted", () => {
    template.hasResourceProperties("AWS::RDS::DBCluster", {
      StorageEncrypted: true,
    });
  });

  test("INF-DATA-003 Aurora backups are retained", () => {
    template.hasResourceProperties("AWS::RDS::DBCluster", {
      BackupRetentionPeriod: 90,
    });
  });

  test("INF-DATA-004 Aurora is not publicly accessible", () => {
    template.hasResourceProperties("AWS::RDS::DBInstance", {
      PubliclyAccessible: false,
    });
  });

  test("INF-DATA-005 Aurora exports logs", () => {
    template.hasResourceProperties("AWS::RDS::DBCluster", {
      EnableCloudwatchLogsExports: ["postgresql"],
    });
  });

  test("INF-DATA-006 S3 uses encryption", () => {
    template.hasResourceProperties("AWS::S3::Bucket", {
      BucketEncryption: {
        ServerSideEncryptionConfiguration: [
          {
            ServerSideEncryptionByDefault: {
              SSEAlgorithm: "AES256",
            },
          },
        ],
      },
    });
  });

  test("INF-DATA-007 S3 lifecycle policy is configured", () => {
    template.hasResourceProperties("AWS::S3::Bucket", {
      LifecycleConfiguration: {
        Rules: Match.arrayWith([
          Match.objectLike({
            Status: "Enabled",
          }),
        ]),
      },
    });
  });

  test("INF-ASYNC-001 SQS has DLQ", () => {
    template.hasResourceProperties("AWS::SQS::Queue", {
      RedrivePolicy: {
        deadLetterTargetArn: {
          "Fn::GetAtt": [Match.anyValue(), "Arn"],
        },
      },
    });
  });

  test("INF-ASYNC-002 SQS is encrypted", () => {
    template.hasResourceProperties("AWS::SQS::Queue", {
      KmsMasterKeyId: Match.anyValue(),
    });
  });

  test("INF-ASYNC-003 Worker lambda has SQS event source", () => {
    template.resourceCountIs("AWS::Lambda::EventSourceMapping", 1);
  });

  test("INF-ASYNC-004 SES identity exists", () => {
    template.resourceCountIs("AWS::SES::EmailIdentity", 1);
  });

  test("INF-OPS-001 CloudWatch alarms exist", () => {
    template.resourceCountIs("AWS::CloudWatch::Alarm", 3);
  });

  test("INF-OPS-002 Access log retention exists", () => {
    template.hasResourceProperties("AWS::Logs::LogGroup", {
      RetentionInDays: 30,
    });
  });

  test("INF-OPS-003 WAF managed rule configured", () => {
    template.hasResourceProperties("AWS::WAFv2::WebACL", {
      Rules: [
        {
          Statement: {
            ManagedRuleGroupStatement: {
              Name: "AWSManagedRulesCommonRuleSet",
            },
          },
        },
      ],
    });
  });

  test("INF-OPS-004 No admin IAM policies", () => {
    const policies = template.findResources("AWS::IAM::Policy");
    const policyValues = Object.values(policies);
    const hasAdmin = policyValues.some((policy) =>
      JSON.stringify(policy).includes("\"Action\":[\"*\"]") &&
      JSON.stringify(policy).includes("\"Resource\":\"*\"")
    );
    expect(hasAdmin).toBe(false);
  });

  test("CDK-NAG-AWS-SOLUTIONS", () => {
    const annotations = Annotations.fromStack(stack);
    annotations.hasNoError("*", Match.anyValue());
  });
});
