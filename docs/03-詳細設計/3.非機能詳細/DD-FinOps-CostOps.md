# FinOps/コスト運用（詳細）

## 対応NFR
NFR-COST-001〜005

## タグ標準
- 必須タグキー
  - service, env, team, owner, costCenter
- 付与ルール
  - すべてのリソースに必須（自動付与）
  - 未付与はCIで検知しデプロイ停止
- 未付与の検知/是正
  - 日次でタグ欠落レポート
  - 24時間以内に是正

## 予算/通知（初期値）
- 月次予算
  - prod: 150,000 JPY
  - stg: 50,000 JPY
  - dev: 30,000 JPY
- 通知
  - 80%到達: Slack通知
  - 100%到達: Slack + Email + Pager
- 通知テスト手順
  - 月次で模擬アラートを送信

## AWSコスト試算（CDKベース）

### 前提
- リージョン: ap-northeast-1
- 環境: 個人開発・単一AZ・冗長性なし
- 稼働時間: 730時間/月
- 為替: 1 USD = 150 JPY
- 想定利用量: HTTP API 100万リクエスト/月、Lambda API 100万回（200ms/2GB）、Lambda Worker 100万回（1s/2GB）、Aurora Serverless v2 平均0.5 ACU（auto pause有効でアイドル時0 ACU）、DBストレージ50GB + バックアップ50GB、S3 50GB + アクセスログ5GB、SQS 100万リクエスト/月、CloudWatch Logs ingest 5GB + 保存10GB
- Aurora自動一時停止: 最小容量0 ACU、idle timeout 5分（最小値）
- Auroraエンジン: Aurora PostgreSQL 16.10（auto pause対応、16.3+が必要）

### リソース一覧/試算
| AWSサービス | リソース名(CDK) | 物理名 | 数 | 月額(概算, JPY) | 備考 |
| --- | --- | --- | --- | --- | --- |
| VPC | Network/Vpc | Tag Name: cornellnote-${env}-vpc | 1 | 0 | VPC本体 |
| VPC付随リソース | Network/Vpc (auto) | CDK自動生成 | 1式 | 0 | isolated subnet x1、RouteTable |
| S3 Bucket | Storage/NotesBucket | CDK自動生成 | 1 | 275 | 50GB想定 |
| S3 Bucket | Storage/NotesAccessLogs | CDK自動生成 | 1 | 25 | 5GB想定 |
| RDS Subnet Group | Database/DbSubnetGroup | CDK自動生成 | 1 | 0 | |
| Aurora Serverless v2 | Database/AuroraCluster | CDK自動生成 | 1 | 5,050 | 平均0.5 ACU + 50GB storage + 50GB backup |
| Secrets Manager | Database/AuroraCluster/Secret | CDK自動生成 | 1 | 60 | DB認証情報 |
| SQS Queue | Queue/WorkerQueue | cornellnote-${env}-worker | 1 | 60 | 100万リクエスト想定 |
| SQS DLQ | Queue/WorkerDlq | cornellnote-${env}-worker-dlq | 1 | 0 | DLQ分は上記に含む |
| Lambda | App/ApiLambda | cornellnote-${env}-api | 1 | 1,040 | 100万回/200ms/2GB |
| Lambda | App/WorkerLambda | cornellnote-${env}-worker | 1 | 5,040 | 100万回/1s/2GB |
| IAM Role/Policy | App/ApiLambdaRole, App/WorkerLambdaRole | CDK自動生成 | 2 | 0 | |
| API Gateway (HTTP API) | Api/HttpApi | cornellnote-${env}-http-api | 1 | 150 | 100万リクエスト想定 |
| API Gateway付随 | Api/ApiIntegration, Api/ApiRoute, Api/DefaultStage | CDK自動生成 | 1式 | 0 | |
| CloudWatch Logs | App/ApiLogGroup, App/WorkerLogGroup, Api/ApiAccessLogs, Database/AuroraCluster (postgresql) | CDK自動生成 | 4 | 450 | ingest 5GB + 保存10GB |
| CloudWatch Alarms | Observability/Api5xxAlarm, Observability/LambdaErrorAlarm0/1 | CDK自動生成 | 3 | 50 | 標準アラーム |
| SES Email Identity | Email/SesIdentity | example.com | 1 | 0 | Identityのみ |

- 月額合計（概算）: 12,200 JPY
- 注記: Aurora Serverless v2のauto pauseは最小容量0 ACUを指定し、5分のidle timeoutが最小値（公式ドキュメント）。DBはRDS Data APIで接続。料金はAWSの課金単価・為替・利用量で変動するため、見積もりの基準値として扱う。開発時は起動時間（再開）を考慮してタイムアウトを調整する。

## 非本番停止
- スケジュール
  - 平日: 20:00-08:00 停止
  - 週末/祝日: 全停止
- 例外申請
  - 24時間前までに申請
  - 影響範囲とコスト見積もり必須

## 異常検知
- 検知条件
  - 日次コストが7日平均の150%超
  - 時間当たりコストが200%超
- 通知経路
  - Slack（即時）/Email（集計）
- 一次対応
  - 暴走ジョブ停止、スケール上限制御

## ライフサイクル
- ログ
  - 30日ホット
  - 90日ウォーム
  - 365日アーカイブ
- バックアップ
  - 90日保持（短期）/ 12ヶ月（週次）
- オブジェクト
  - 30日でIA
  - 365日でアーカイブ
  - 730日で削除
