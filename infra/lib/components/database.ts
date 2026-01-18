import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import * as ec2 from "aws-cdk-lib/aws-ec2";
import * as rds from "aws-cdk-lib/aws-rds";
import * as secretsmanager from "aws-cdk-lib/aws-secretsmanager";
import { NagSuppressions } from "cdk-nag";

interface DatabaseComponentProps {
  vpc: ec2.IVpc;
  envName: string;
}

export class DatabaseComponent extends Construct {
  readonly cluster: rds.DatabaseCluster;
  readonly secret: secretsmanager.ISecret;

  constructor(scope: Construct, id: string, props: DatabaseComponentProps) {
    super(scope, id);

    const engineVersion = rds.AuroraPostgresEngineVersion.of("16.10", "16", {
      serverlessV2AutoPauseSupported: true
    });

    const subnetGroup = new rds.SubnetGroup(this, "DbSubnetGroup", {
      vpc: props.vpc,
      description: "Isolated subnets for Aurora",
      vpcSubnets: { subnetType: ec2.SubnetType.PRIVATE_ISOLATED }
    });

    this.cluster = new rds.DatabaseCluster(this, "AuroraCluster", {
      engine: rds.DatabaseClusterEngine.auroraPostgres({
        version: engineVersion
      }),
      writer: rds.ClusterInstance.serverlessV2("writer", {
        publiclyAccessible: false,
        enablePerformanceInsights: false
      }),
      vpc: props.vpc,
      vpcSubnets: { subnetType: ec2.SubnetType.PRIVATE_ISOLATED },
      subnetGroup,
      defaultDatabaseName: "cornellnote",
      storageEncrypted: true,
      backup: {
        retention: cdk.Duration.days(7),
        preferredWindow: "18:00-19:00"
      },
      iamAuthentication: true,
      enableDataApi: true,
      serverlessV2MinCapacity: 0,
      serverlessV2MaxCapacity: 2,
      removalPolicy: cdk.RemovalPolicy.DESTROY,
      publiclyAccessible: false,
      deletionProtection: false,
      cloudwatchLogsExports: ["postgresql"]
    });

    const secret = this.cluster.secret;
    if (!secret) {
      throw new Error("Database secret was not created");
    }
    this.secret = secret;

    const clusterResource = this.cluster.node.defaultChild as rds.CfnDBCluster;
    clusterResource.serverlessV2ScalingConfiguration = {
      minCapacity: 0,
      maxCapacity: 2,
      secondsUntilAutoPause: 300
    };

    const secretResource = this.cluster.node.findChild("Secret");
    NagSuppressions.addResourceSuppressions(
      secretResource,
      [
        {
          id: "AwsSolutions-SMG4",
          reason: "Aurora Serverless v2の自動ローテーションは運用手順で管理"
        }
      ],
      true
    );
  }
}
