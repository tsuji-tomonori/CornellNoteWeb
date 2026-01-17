import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import * as ec2 from "aws-cdk-lib/aws-ec2";
import * as lambda from "aws-cdk-lib/aws-lambda";
import * as lambdaEventSources from "aws-cdk-lib/aws-lambda-event-sources";
import * as logs from "aws-cdk-lib/aws-logs";
import * as secretsmanager from "aws-cdk-lib/aws-secretsmanager";
import * as rds from "aws-cdk-lib/aws-rds";
import * as s3 from "aws-cdk-lib/aws-s3";
import * as sqs from "aws-cdk-lib/aws-sqs";

interface AppComponentProps {
  vpc: ec2.IVpc;
  appSecurityGroup: ec2.ISecurityGroup;
  dbSecret: secretsmanager.ISecret;
  dbCluster: rds.IDatabaseCluster;
  storageBucket: s3.IBucket;
  queue: sqs.IQueue;
  envName: string;
}

export class AppComponent extends Construct {
  readonly apiLambda: lambda.Function;
  readonly workerLambda: lambda.Function;

  constructor(scope: Construct, id: string, props: AppComponentProps) {
    super(scope, id);

    const commonEnv = {
      DB_SECRET_ARN: props.dbSecret.secretArn,
      DB_CLUSTER_ARN: props.dbCluster.clusterArn,
      NOTES_BUCKET: props.storageBucket.bucketName,
      ENV_NAME: props.envName,
      STAGE: props.envName,
    };

    const apiLogGroup = new logs.LogGroup(this, "ApiLogGroup", {
      retention: logs.RetentionDays.ONE_MONTH,
    });

    this.apiLambda = new lambda.Function(this, "ApiLambda", {
      functionName: `cornellnote-${props.envName}-api`,
      runtime: lambda.Runtime.JAVA_21,
      memorySize: 2048,
      timeout: cdk.Duration.seconds(30),
      handler: "not.used",
      code: lambda.Code.fromAsset("../build/app.zip"),
      vpc: props.vpc,
      securityGroups: [props.appSecurityGroup],
      logGroup: apiLogGroup,
      tracing: lambda.Tracing.ACTIVE,
      reservedConcurrentExecutions: props.envName === "prod" ? 200 : 50,
      environment: {
        ...commonEnv,
        HANDLER_TYPE: "api",
      },
    });

    const workerLogGroup = new logs.LogGroup(this, "WorkerLogGroup", {
      retention: logs.RetentionDays.ONE_MONTH,
    });

    this.workerLambda = new lambda.Function(this, "WorkerLambda", {
      functionName: `cornellnote-${props.envName}-worker`,
      runtime: lambda.Runtime.JAVA_21,
      memorySize: 2048,
      timeout: cdk.Duration.minutes(5),
      handler: "not.used",
      code: lambda.Code.fromAsset("../build/app.zip"),
      vpc: props.vpc,
      securityGroups: [props.appSecurityGroup],
      logGroup: workerLogGroup,
      tracing: lambda.Tracing.ACTIVE,
      environment: {
        ...commonEnv,
        HANDLER_TYPE: "worker",
        QUEUE_URL: props.queue.queueUrl,
      },
    });

    this.workerLambda.addEventSource(
      new lambdaEventSources.SqsEventSource(props.queue, {
        batchSize: 5,
      })
    );

    props.dbSecret.grantRead(this.apiLambda);
    props.dbSecret.grantRead(this.workerLambda);
    props.storageBucket.grantReadWrite(this.apiLambda);
    props.storageBucket.grantReadWrite(this.workerLambda);
    props.queue.grantConsumeMessages(this.workerLambda);
  }
}
