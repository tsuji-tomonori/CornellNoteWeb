import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import * as ec2 from "aws-cdk-lib/aws-ec2";
import * as logs from "aws-cdk-lib/aws-logs";

interface NetworkComponentProps {
  envName: string;
}

export class NetworkComponent extends Construct {
  readonly vpc: ec2.Vpc;

  constructor(scope: Construct, id: string, props: NetworkComponentProps) {
    super(scope, id);

    this.vpc = new ec2.Vpc(this, "Vpc", {
      maxAzs: 2,
      natGateways: props.envName === "prod" ? 2 : 1,
      subnetConfiguration: [
        {
          name: "public",
          subnetType: ec2.SubnetType.PUBLIC,
        },
        {
          name: "private",
          subnetType: ec2.SubnetType.PRIVATE_WITH_EGRESS,
        },
        {
          name: "isolated",
          subnetType: ec2.SubnetType.PRIVATE_ISOLATED,
        },
      ],
    });

    const flowLogsGroup = new logs.LogGroup(this, "VpcFlowLogs", {
      retention: logs.RetentionDays.ONE_MONTH,
    });

    this.vpc.addFlowLog("VpcFlowLogs", {
      destination: ec2.FlowLogDestination.toCloudWatchLogs(flowLogsGroup),
      trafficType: ec2.FlowLogTrafficType.ALL,
    });

    cdk.Tags.of(this.vpc).add("Name", `cornellnote-${props.envName}-vpc`);
  }
}
