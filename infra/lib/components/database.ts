import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import * as ec2 from "aws-cdk-lib/aws-ec2";
import * as rds from "aws-cdk-lib/aws-rds";
import * as secretsmanager from "aws-cdk-lib/aws-secretsmanager";

interface DatabaseComponentProps {
  vpc: ec2.IVpc;
  appSecurityGroup: ec2.ISecurityGroup;
  envName: string;
}

export class DatabaseComponent extends Construct {
  readonly cluster: rds.DatabaseCluster;
  readonly secret: secretsmanager.ISecret;

  constructor(scope: Construct, id: string, props: DatabaseComponentProps) {
    super(scope, id);

    const subnetGroup = new rds.SubnetGroup(this, "DbSubnetGroup", {
      vpc: props.vpc,
      description: "Isolated subnets for Aurora",
      vpcSubnets: { subnetType: ec2.SubnetType.PRIVATE_ISOLATED },
    });

    this.cluster = new rds.DatabaseCluster(this, "AuroraCluster", {
      engine: rds.DatabaseClusterEngine.auroraPostgres({
        version: rds.AuroraPostgresEngineVersion.VER_15_4,
      }),
      writer: rds.ClusterInstance.serverlessV2("writer", {
        enablePerformanceInsights: true,
        publiclyAccessible: false,
      }),
      readers: [
        rds.ClusterInstance.serverlessV2("reader", {
          enablePerformanceInsights: true,
          publiclyAccessible: false,
        }),
      ],
      vpc: props.vpc,
      vpcSubnets: { subnetType: ec2.SubnetType.PRIVATE_ISOLATED },
      subnetGroup,
      defaultDatabaseName: "cornellnote",
      storageEncrypted: true,
      backup: {
        retention: cdk.Duration.days(90),
        preferredWindow: "18:00-19:00",
      },
      serverlessV2MinCapacity: 0.5,
      serverlessV2MaxCapacity: props.envName === "prod" ? 4 : 2,
      deletionProtection: props.envName === "prod",
      cloudwatchLogsExports: ["postgresql"],
    });

    this.cluster.connections.allowDefaultPortFrom(
      props.appSecurityGroup,
      "Allow app access to Aurora"
    );

    const secret = this.cluster.secret;
    if (!secret) {
      throw new Error("Database secret was not created");
    }
    this.secret = secret;
  }
}
