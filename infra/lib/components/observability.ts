import { Construct } from "constructs";
import * as cloudwatch from "aws-cdk-lib/aws-cloudwatch";
import * as lambda from "aws-cdk-lib/aws-lambda";
import * as apigwv2 from "aws-cdk-lib/aws-apigatewayv2";

interface ObservabilityComponentProps {
  api: apigwv2.IHttpApi;
  lambdas: lambda.IFunction[];
}

export class ObservabilityComponent extends Construct {
  constructor(scope: Construct, id: string, props: ObservabilityComponentProps) {
    super(scope, id);

    new cloudwatch.Alarm(this, "Api5xxAlarm", {
      metric: props.api.metricServerError(),
      threshold: 1,
      evaluationPeriods: 1,
      datapointsToAlarm: 1,
    });

    props.lambdas.forEach((fn, index) => {
      new cloudwatch.Alarm(this, `LambdaErrorAlarm${index}`, {
        metric: fn.metricErrors(),
        threshold: 1,
        evaluationPeriods: 1,
        datapointsToAlarm: 1,
      });
    });
  }
}
