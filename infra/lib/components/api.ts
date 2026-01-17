import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import * as apigwv2 from "aws-cdk-lib/aws-apigatewayv2";
import * as iam from "aws-cdk-lib/aws-iam";
import * as lambda from "aws-cdk-lib/aws-lambda";
import * as logs from "aws-cdk-lib/aws-logs";
import * as wafv2 from "aws-cdk-lib/aws-wafv2";

interface ApiComponentProps {
  handler: lambda.IFunction;
  envName: string;
}

export class ApiComponent extends Construct {
  readonly api: apigwv2.IHttpApi;

  constructor(scope: Construct, id: string, props: ApiComponentProps) {
    super(scope, id);

    const accessLogs = new logs.LogGroup(this, "ApiAccessLogs", {
      retention: logs.RetentionDays.ONE_MONTH,
    });

    const api = new apigwv2.CfnApi(this, "HttpApi", {
      name: `cornellnote-${props.envName}-http-api`,
      protocolType: "HTTP",
    });

    const integration = new apigwv2.CfnIntegration(this, "ApiIntegration", {
      apiId: api.ref,
      integrationType: "AWS_PROXY",
      integrationUri: props.handler.functionArn,
      payloadFormatVersion: "2.0",
      integrationMethod: "POST",
    });

    const route = new apigwv2.CfnRoute(this, "ApiRoute", {
      apiId: api.ref,
      routeKey: "ANY /{proxy+}",
      target: `integrations/${integration.ref}`,
    });

    const stage = new apigwv2.CfnStage(this, "DefaultStage", {
      apiId: api.ref,
      stageName: "$default",
      autoDeploy: true,
      accessLogSettings: {
        destinationArn: accessLogs.logGroupArn,
        format: JSON.stringify({
          requestId: "$context.requestId",
          requestTime: "$context.requestTime",
          httpMethod: "$context.httpMethod",
          routeKey: "$context.routeKey",
          status: "$context.status",
          responseLength: "$context.responseLength",
          integrationError: "$context.integrationErrorMessage",
        }),
      },
      defaultRouteSettings: {
        throttlingBurstLimit: 200,
        throttlingRateLimit: 100,
      },
    });
    stage.addDependency(route);

    this.api = apigwv2.HttpApi.fromHttpApiAttributes(this, "HttpApiRef", {
      httpApiId: api.ref,
      apiEndpoint: api.attrApiEndpoint,
    });

    props.handler.addPermission("ApiInvoke", {
      principal: new iam.ServicePrincipal("apigateway.amazonaws.com"),
      sourceArn: cdk.Stack.of(this).formatArn({
        service: "execute-api",
        resource: api.ref,
        resourceName: "*/*",
      }),
    });

    const webAcl = new wafv2.CfnWebACL(this, "ApiWebAcl", {
      defaultAction: { allow: {} },
      scope: "REGIONAL",
      visibilityConfig: {
        cloudWatchMetricsEnabled: true,
        metricName: `cornellnote-${props.envName}-waf`,
        sampledRequestsEnabled: true,
      },
      rules: [
        {
          name: "AWSManagedRulesCommonRuleSet",
          priority: 0,
          overrideAction: { none: {} },
          statement: {
            managedRuleGroupStatement: {
              name: "AWSManagedRulesCommonRuleSet",
              vendorName: "AWS",
            },
          },
          visibilityConfig: {
            cloudWatchMetricsEnabled: true,
            metricName: `cornellnote-${props.envName}-common`,
            sampledRequestsEnabled: true,
          },
        },
      ],
    });

    const stageArn = cdk.Stack.of(this).formatArn({
      service: "apigateway",
      resource: "/apis",
      resourceName: `${api.ref}/stages/$default`,
    });

    new wafv2.CfnWebACLAssociation(this, "ApiWebAclAssoc", {
      resourceArn: stageArn,
      webAclArn: webAcl.attrArn,
    });
  }
}
