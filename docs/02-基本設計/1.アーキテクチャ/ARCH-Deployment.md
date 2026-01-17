# デプロイ構成（物理）

## 環境
- dev / stg / prod を分離（NFR-DEV-007）
- IaCで構成管理（NFR-DEV-001）

## 構成概要（AWS, コスト最適化）
```mermaid
flowchart LR
  user((User)) --> r53[Route53]
  r53 --> apigw[API Gateway (HTTP API)]
  apigw --> lambda[Lambda + Web Adapter (Spring Boot SSR/API)]
  lambda --> rds[(RDS/Aurora Serverless)]
  lambda --> s3obj[(S3 Object Storage)]
  lambda --> ses[SES]
  lambda --> idp[Google OAuth]
  lambda --> sqs[SQS]
  sqs --> worker[Lambda Worker]
```

## ネットワーク/セキュリティ
- API Gateway (HTTP API)にWAFを適用
- RDSはVPC内のプライベートサブネット
- LambdaはVPC内からDBに接続
- S3は署名URLでアクセス
- IAMロールで最小権限

## 可用性/運用
- Lambdaの自動スケールでバースト対応
- DBはMulti-AZを前提
- 監視/アラートはCloudWatch + NFR-OPSに準拠

## コスト最適化の意図
- 常時稼働を避け、従量課金のAPI Gateway (HTTP API) + Lambdaを採用
- 非同期処理はSQS + Lambdaでオンデマンド実行
