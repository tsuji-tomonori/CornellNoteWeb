import { Construct } from "constructs";
import * as ec2 from "aws-cdk-lib/aws-ec2";

interface SecurityComponentProps {
  vpc: ec2.IVpc;
}

export class SecurityComponent extends Construct {
  readonly appSecurityGroup: ec2.SecurityGroup;

  constructor(scope: Construct, id: string, props: SecurityComponentProps) {
    super(scope, id);

    this.appSecurityGroup = new ec2.SecurityGroup(this, "AppSecurityGroup", {
      vpc: props.vpc,
      description: "Security group for app lambdas",
      allowAllOutbound: true
    });
  }
}
