# CloudWatch Logs authorization migration

AWS Health deadline reported for this account: 2027-04-26 (verify the account's notification before rollout).

## Repository finding

The API and worker roles previously granted DescribeLogStreams using LogGroup.logGroupArn.
CDK returns that attribute with a trailing :*. Appending :log-stream:* to that attribute
also produces an unnecessary :*:log-stream:* suffix.

The fix grants DescribeLogStreams on the standard ARN built from the same log group name,
partition, region and account. CreateLogStream and PutLogEvents use that same ARN plus
:log-stream:*. It adds no actions and no other log groups. GetLogEvents and DeleteLogStream
remain ungranted by these statements.

This is a source-code finding, not confirmation that these roles are deployed in the
account that received the notification. No live IAM policy was changed.

## Read-only account inspection

Use an authenticated AWS CLI v2 profile for the notified account, plus jq.
Required permission: iam:GetAccountAuthorizationDetails. Verify the account first:

```bash
export AWS_PROFILE=YOUR_PROFILE
export AWS_PAGER=""
aws sts get-caller-identity
set -o pipefail
aws iam get-account-authorization-details \
  --filter LocalManagedPolicy Role User Group \
  --output json | jq -r '
  def arr: if type=="array" then . else [.] end;
  def old: test("^arn:aws[a-z0-9-]*:logs:[^:]*:[^:]*:log-group:.*(:\\*$|:log-stream:)");

  # Scan one policy document, emitting indented "<Effect>\t<Field>\t<Arn>" lines.
  def scan:
    (.Statement|arr)[] | .Effect as $e
    | ("Resource","NotResource") as $f
    | (.[$f]//empty|arr)[]
    | select(type=="string" and old)
    | "    \($e // "Unknown")\t\($f)\t\(.)";

  # Print header then hits, only if the doc has any.
  def report($hdr; doc): [doc|scan] | select(length>0) | ($hdr, .[]);

  ( .Policies[]
    | report("MANAGED  \(.Arn)";
        (.PolicyVersionList[]|select(.IsDefaultVersion)|.Document)) ),
  ( .UserDetailList[]  | .Arn as $a | .UserPolicyList[]?
    | report("INLINE   \($a) [\(.PolicyName)]"; .PolicyDocument) ),
  ( .GroupDetailList[] | .Arn as $a | .GroupPolicyList[]?
    | report("INLINE   \($a) [\(.PolicyName)]"; .PolicyDocument) ),
  ( .RoleDetailList[]  | .Arn as $a | .RolePolicyList[]?
    | report("INLINE   \($a) [\(.PolicyName)]"; .PolicyDocument) )
'
```

The command above is from AWS's LIST_IMPACTED_POLICIES.md. A hit is a candidate, not
proof of a lost permission: it does not filter actions or account for other statements.
Review Allow/Deny, Resource/NotResource, conditions, and other grants together.
A successful empty result only clears the customer-managed default versions and
user/group/role inline policies covered by this command; AWS-managed policies and
other authorization layers are outside its scope. A failed CLI call is not a clean result.

## Compare policy documents

Obtain the affected role's current inline policy (use the actual role and policy names):

```bash
aws iam get-role-policy --role-name YOUR_ROLE --policy-name YOUR_POLICY \
  --query PolicyDocument --output json > before.json
```

This additional command requires iam:GetRolePolicy. Alternatively extract the document
from GetAccountAuthorizationDetails. For a customer-managed policy, inspect its default
version. Prepare after.json from the proposed policy, resolving CloudFormation tokens to
the actual account, region and log group names. Preserve all unrelated statements and
conditions. Do not submit an unresolved CDK template as a policy document.

Download and review the AWS validator, then run both documents against both actual
API and worker log groups. The simulator requires Python 3.8+, authenticated AWS CLI v2,
and iam:SimulateCustomPolicy.

```bash
git clone https://github.com/aws-samples/sample-cloudwatch-logs-iam-policy-validator.git
export LOGS_REGION=ap-northeast-1
export LOGS_GROUP='ACTUAL_LOG_GROUP_NAME'
LOGS_ACCOUNT=$(aws sts get-caller-identity --query Account --output text)
set -o pipefail
python3 sample-cloudwatch-logs-iam-policy-validator/simulate_cloudwatch_logs_policy.py \
  before.json "$LOGS_GROUP" --region "$LOGS_REGION" --account-id "$LOGS_ACCOUNT" | tee before.txt
python3 sample-cloudwatch-logs-iam-policy-validator/simulate_cloudwatch_logs_policy.py \
  after.json "$LOGS_GROUP" --region "$LOGS_REGION" --account-id "$LOGS_ACCOUNT" | tee after.txt
diff -u before.txt after.txt || test "$?" -eq 1
```

Use the actual region rather than assuming ap-northeast-1. Record the validator commit
with git -C sample-cloudwatch-logs-iam-policy-validator rev-parse HEAD.

Expected results for the changed statements, not measured live results:

| Action | Before standard LogGroup | After standard LogGroup | After LogStream |
| --- | --- | --- | --- |
| DescribeLogStreams | implicitDeny | allowed | implicitDeny |
| CreateLogStream | implicitDeny | implicitDeny | allowed |
| PutLogEvents | implicitDeny | implicitDeny | allowed |

Repeat against an unrelated log group: these statements must not allow any of the
three actions there. Check GetLogEvents and DeleteLogStream remain denied unless an
existing independent policy intentionally permits them. Do not grant all actions merely
to make the simulator's full action table show allowed.

The two output columns compare ARN forms; they are not two live CloudWatch service
versions. MissingContextValues, notSimulated, errors, or explicitDeny require investigation.
Custom-policy simulation is not proof of effective access under every SCP, permissions
boundary, resource policy, session policy or runtime condition.

After separately reviewed deployment, verify actual log output and DescribeLogStreams
for both roles and rerun the account inspection.

## Validation performed

- npm ci --ignore-scripts: passed.
- npm run build: passed.
- Focused CDK synthesis regression test for both roles: passed.
- Full Jest suite: blocked by missing application asset Dockerfile.lambda in the
  infrastructure-only local checkout. It was not reported as passing.
- AWS account inspection and official IAM simulation: not completed. AWS CLI was not
  installed in the execution environment; the AWS connection identity request was
  interrupted without a result.

## Sources

- https://github.com/aws-samples/sample-cloudwatch-logs-iam-policy-validator/blob/main/LIST_IMPACTED_POLICIES.md
- https://github.com/aws-samples/sample-cloudwatch-logs-iam-policy-validator/blob/main/README.md
- https://github.com/aws/aws-cdk/blob/main/packages/aws-cdk-lib/aws-logs/lib/log-group.ts
