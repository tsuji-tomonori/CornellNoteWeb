import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import * as iam from "aws-cdk-lib/aws-iam";
import * as lambda from "aws-cdk-lib/aws-lambda";
import * as lambdaEventSources from "aws-cdk-lib/aws-lambda-event-sources";
import * as logs from "aws-cdk-lib/aws-logs";
import { NagSuppressions } from "cdk-nag";
import * as secretsmanager from "aws-cdk-lib/aws-secretsmanager";
import * as rds from "aws-cdk-lib/aws-rds";
import * as s3 from "aws-cdk-lib/aws-s3";
import * as sqs from "aws-cdk-lib/aws-sqs";
import * as path from "path";

interface AppComponentProps {
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
      SPRING_PROFILES_ACTIVE: "lambda"
    };

    const apiLogGroup = new logs.LogGroup(this, "ApiLogGroup", {
      retention: logs.RetentionDays.ONE_MONTH
    });

    const workerLogGroup = new logs.LogGroup(this, "WorkerLogGroup", {
      retention: logs.RetentionDays.ONE_MONTH
    });

    const apiRole = new iam.Role(this, "ApiLambdaRole", {
      assumedBy: new iam.ServicePrincipal("lambda.amazonaws.com")
    });

    apiRole.addToPolicy(
      new iam.PolicyStatement({
        actions: ["rds-data:ExecuteStatement", "rds-data:BatchExecuteStatement"],
        resources: [props.dbCluster.clusterArn]
      })
    );

    apiRole.addToPolicy(
      new iam.PolicyStatement({
        actions: ["secretsmanager:GetSecretValue"],
        resources: [props.dbSecret.secretArn]
      })
    );

    apiRole.addToPolicy(
      new iam.PolicyStatement({
        actions: ["logs:CreateLogStream", "logs:PutLogEvents", "logs:DescribeLogStreams"],
        resources: [apiLogGroup.logGroupArn, `${apiLogGroup.logGroupArn}:log-stream:*`]
      })
    );

    apiRole.addToPolicy(
      new iam.PolicyStatement({
        actions: [
          "s3:GetObject",
          "s3:PutObject",
          "s3:DeleteObject",
          "s3:AbortMultipartUpload",
          "s3:ListMultipartUploadParts"
        ],
        resources: [`${props.storageBucket.bucketArn}/*`]
      })
    );

    apiRole.addToPolicy(
      new iam.PolicyStatement({
        actions: ["s3:ListBucket", "s3:GetBucketLocation", "s3:ListBucketMultipartUploads"],
        resources: [props.storageBucket.bucketArn]
      })
    );

    const workerRole = new iam.Role(this, "WorkerLambdaRole", {
      assumedBy: new iam.ServicePrincipal("lambda.amazonaws.com")
    });

    workerRole.addToPolicy(
      new iam.PolicyStatement({
        actions: ["rds-data:ExecuteStatement", "rds-data:BatchExecuteStatement"],
        resources: [props.dbCluster.clusterArn]
      })
    );

    workerRole.addToPolicy(
      new iam.PolicyStatement({
        actions: ["secretsmanager:GetSecretValue"],
        resources: [props.dbSecret.secretArn]
      })
    );

    workerRole.addToPolicy(
      new iam.PolicyStatement({
        actions: ["logs:CreateLogStream", "logs:PutLogEvents", "logs:DescribeLogStreams"],
        resources: [workerLogGroup.logGroupArn, `${workerLogGroup.logGroupArn}:log-stream:*`]
      })
    );

    workerRole.addToPolicy(
      new iam.PolicyStatement({
        actions: [
          "s3:GetObject",
          "s3:PutObject",
          "s3:DeleteObject",
          "s3:AbortMultipartUpload",
          "s3:ListMultipartUploadParts"
        ],
        resources: [`${props.storageBucket.bucketArn}/*`]
      })
    );

    workerRole.addToPolicy(
      new iam.PolicyStatement({
        actions: ["s3:ListBucket", "s3:GetBucketLocation", "s3:ListBucketMultipartUploads"],
        resources: [props.storageBucket.bucketArn]
      })
    );

    this.apiLambda = new lambda.DockerImageFunction(this, "ApiLambda", {
      functionName: `cornellnote-${props.envName}-api`,
      code: lambda.DockerImageCode.fromImageAsset(path.join(__dirname, "../../.."), {
        file: "Dockerfile.lambda"
      }),
      memorySize: 2048,
      timeout: cdk.Duration.seconds(30),
      logGroup: apiLogGroup,
      role: apiRole,
      tracing: lambda.Tracing.ACTIVE,
      reservedConcurrentExecutions: props.envName === "prod" ? 200 : 50,
      environment: {
        ...commonEnv,
        HANDLER_TYPE: "api"
      }
    });

    this.workerLambda = new lambda.Function(this, "WorkerLambda", {
      functionName: `cornellnote-${props.envName}-worker`,
      runtime: lambda.Runtime.JAVA_21,
      memorySize: 2048,
      timeout: cdk.Duration.minutes(5),
      handler: "not.used",
      code: lambda.Code.fromAsset("../build/app.zip"),
      logGroup: workerLogGroup,
      role: workerRole,
      tracing: lambda.Tracing.ACTIVE,
      environment: {
        ...commonEnv,
        HANDLER_TYPE: "worker",
        QUEUE_URL: props.queue.queueUrl
      }
    });

    this.workerLambda.addEventSource(
      new lambdaEventSources.SqsEventSource(props.queue, {
        batchSize: 5
      })
    );

    props.queue.grantConsumeMessages(this.workerLambda);

    const apiDefaultPolicy = apiRole.node.findChild("DefaultPolicy") as iam.Policy;
    const workerDefaultPolicy = workerRole.node.findChild("DefaultPolicy") as iam.Policy;

    NagSuppressions.addResourceSuppressions(
      apiDefaultPolicy.node.defaultChild as iam.CfnPolicy,
      [
        {
          id: "AwsSolutions-IAM5",
          reason: "アプリ実行に必要な権限で一部ワイルドカードが避けられない"
        }
      ],
      true
    );

    NagSuppressions.addResourceSuppressions(
      workerDefaultPolicy.node.defaultChild as iam.CfnPolicy,
      [
        {
          id: "AwsSolutions-IAM5",
          reason: "ワーカー実行に必要な権限で一部ワイルドカードが避けられない"
        }
      ],
      true
    );

    NagSuppressions.addResourceSuppressions(this.apiLambda, [
      {
        id: "AwsSolutions-L1",
        reason: "Lambda Web Adapterを利用するコンテナランタイムのため"
      }
    ]);

    NagSuppressions.addResourceSuppressions(this.workerLambda, [
      {
        id: "AwsSolutions-L1",
        reason: "Java 21を利用するためランタイムを固定"
      }
    ]);
  }
}
