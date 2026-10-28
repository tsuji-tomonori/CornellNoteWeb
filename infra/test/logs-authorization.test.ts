import { App, Stack } from "aws-cdk-lib";
import { Template } from "aws-cdk-lib/assertions";
import * as ecr from "aws-cdk-lib/aws-ecr";
import * as lambda from "aws-cdk-lib/aws-lambda";
import * as rds from "aws-cdk-lib/aws-rds";
import * as s3 from "aws-cdk-lib/aws-s3";
import * as secretsmanager from "aws-cdk-lib/aws-secretsmanager";
import * as sqs from "aws-cdk-lib/aws-sqs";
import { AppComponent } from "../lib/components/app";

test("Lambda log permissions separate group discovery from stream writes", () => {
  const stack = new Stack(new App(), "LogsAuthorization", {
    env: { account: "123456789012", region: "ap-northeast-1" }
  });
  const bucket = new s3.Bucket(stack, "Storage");
  const repository = ecr.Repository.fromRepositoryName(stack, "Image", "test-image");
  const image = jest
    .spyOn(lambda.DockerImageCode, "fromImageAsset")
    .mockReturnValue(lambda.DockerImageCode.fromEcr(repository));
  const code = jest
    .spyOn(lambda.Code, "fromAsset")
    .mockReturnValue(new lambda.AssetCode(__dirname));
  try {
    const component = new AppComponent(stack, "Application", {
      dbSecret: new secretsmanager.Secret(stack, "Secret"),
      dbCluster: rds.DatabaseCluster.fromDatabaseClusterAttributes(stack, "Database", {
        clusterIdentifier: "test-database",
        port: 5432
      }),
      storageBucket: bucket,
      queue: new sqs.Queue(stack, "Queue"),
      envName: "dev"
    });
    const template = Template.fromStack(stack).toJSON();
    for (const [roleName, groupName] of [
      ["ApiLambdaRole", "ApiLogGroup"],
      ["WorkerLambdaRole", "WorkerLogGroup"]
    ]) {
      const role = component.node.findChild(roleName);
      const group = component.node.findChild(groupName);
      const roleId = stack.getLogicalId(
        role.node.defaultChild as import("aws-cdk-lib").CfnResource
      );
      const groupId = stack.getLogicalId(
        group.node.defaultChild as import("aws-cdk-lib").CfnResource
      );
      const policy = Object.values(template.Resources).find((resource: unknown) => {
        const value = resource as { Type: string; Properties: { Roles?: unknown[] } };
        return (
          value.Type === "AWS::IAM::Policy" &&
          JSON.stringify(value.Properties.Roles) === JSON.stringify([{ Ref: roleId }])
        );
      }) as {
        Properties: {
          PolicyDocument: {
            Statement: Array<{ Action: string | string[]; Resource: unknown; Effect: string }>;
          };
        };
      };
      expect(policy).toBeDefined();
      const statements = policy.Properties.PolicyDocument.Statement;
      const groupArn = {
        "Fn::Join": [
          "",
          [
            "arn:",
            { Ref: "AWS::Partition" },
            ":logs:ap-northeast-1:123456789012:log-group:",
            { Ref: groupId }
          ]
        ]
      };
      const streamArn = {
        "Fn::Join": [
          "",
          [
            "arn:",
            { Ref: "AWS::Partition" },
            ":logs:ap-northeast-1:123456789012:log-group:",
            { Ref: groupId },
            ":log-stream:*"
          ]
        ]
      };
      expect(statements).toContainEqual({
        Action: "logs:DescribeLogStreams",
        Effect: "Allow",
        Resource: groupArn
      });
      expect(statements).toContainEqual({
        Action: ["logs:CreateLogStream", "logs:PutLogEvents"],
        Effect: "Allow",
        Resource: streamArn
      });
      for (const statement of statements) {
        const actions = Array.isArray(statement.Action) ? statement.Action : [statement.Action];
        if (actions.includes("logs:DescribeLogStreams")) {
          expect(statement.Resource).toEqual(groupArn);
        }
      }
    }
  } finally {
    image.mockRestore();
    code.mockRestore();
  }
});
