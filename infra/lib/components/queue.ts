import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import * as sqs from "aws-cdk-lib/aws-sqs";

interface QueueComponentProps {
  envName: string;
}

export class QueueComponent extends Construct {
  readonly queue: sqs.Queue;

  constructor(scope: Construct, id: string, props: QueueComponentProps) {
    super(scope, id);

    const deadLetterQueue = new sqs.Queue(this, "WorkerDlq", {
      queueName: `cornellnote-${props.envName}-worker-dlq`,
      retentionPeriod: cdk.Duration.days(14),
      encryption: sqs.QueueEncryption.KMS_MANAGED,
    });

    this.queue = new sqs.Queue(this, "WorkerQueue", {
      queueName: `cornellnote-${props.envName}-worker`,
      visibilityTimeout: cdk.Duration.seconds(120),
      retentionPeriod: cdk.Duration.days(4),
      encryption: sqs.QueueEncryption.KMS_MANAGED,
      deadLetterQueue: {
        queue: deadLetterQueue,
        maxReceiveCount: 5,
      },
    });
  }
}
