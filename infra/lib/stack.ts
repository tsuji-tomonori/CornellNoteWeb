import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import { Tags } from "aws-cdk-lib";
import { NetworkComponent } from "./components/network";
import { StorageComponent } from "./components/storage";
import { DatabaseComponent } from "./components/database";
import { QueueComponent } from "./components/queue";
import { AppComponent } from "./components/app";
import { ApiComponent } from "./components/api";
import { ObservabilityComponent } from "./components/observability";
import { SecurityComponent } from "./components/security";
import { EmailComponent } from "./components/email";

interface CornellNoteStackProps extends cdk.StackProps {
  envName: string;
}

export class CornellNoteStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props: CornellNoteStackProps) {
    super(scope, id, props);

    Tags.of(this).add("service", "cornellnote");
    Tags.of(this).add("env", props.envName);
    Tags.of(this).add("team", "platform");
    Tags.of(this).add("owner", "cornellnote");
    Tags.of(this).add("costCenter", "product");

    const network = new NetworkComponent(this, "Network", {
      envName: props.envName,
    });

    const security = new SecurityComponent(this, "Security", {
      vpc: network.vpc,
    });

    const storage = new StorageComponent(this, "Storage");

    const database = new DatabaseComponent(this, "Database", {
      vpc: network.vpc,
      appSecurityGroup: security.appSecurityGroup,
      envName: props.envName,
    });

    const queue = new QueueComponent(this, "Queue", {
      envName: props.envName,
    });

    new EmailComponent(this, "Email", {
      domain: "example.com",
    });

    const app = new AppComponent(this, "App", {
      vpc: network.vpc,
      appSecurityGroup: security.appSecurityGroup,
      dbSecret: database.secret,
      dbCluster: database.cluster,
      storageBucket: storage.bucket,
      queue: queue.queue,
      envName: props.envName,
    });

    const api = new ApiComponent(this, "Api", {
      handler: app.apiLambda,
      envName: props.envName,
    });

    new ObservabilityComponent(this, "Observability", {
      api: api.api,
      lambdas: [app.apiLambda, app.workerLambda],
    });
  }
}
