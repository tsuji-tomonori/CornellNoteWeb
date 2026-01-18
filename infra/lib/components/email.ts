import { Construct } from "constructs";
import * as ses from "aws-cdk-lib/aws-ses";

interface EmailComponentProps {
  domain: string;
}

export class EmailComponent extends Construct {
  readonly identity: ses.EmailIdentity;

  constructor(scope: Construct, id: string, props: EmailComponentProps) {
    super(scope, id);

    this.identity = new ses.EmailIdentity(this, "SesIdentity", {
      identity: ses.Identity.domain(props.domain)
    });
  }
}
