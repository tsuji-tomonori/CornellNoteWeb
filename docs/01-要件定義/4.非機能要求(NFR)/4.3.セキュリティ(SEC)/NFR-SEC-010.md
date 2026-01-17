# NFR-SEC-010 鍵・証明書のローテーション

## Snow Card
- **Requirement ID**: NFR-SEC-010
- **Type**: Non-functional（Security/Crypto）
- **Description（INCOSE形式）**: 本システムは、暗号鍵およびサーバ証明書の有効期限を管理し、鍵は少なくとも年1回、証明書は有効期限前に自動更新しなければならない。
- **Rationale**: 鍵漏えい時の影響低減と、証明書期限切れによる停止防止のため。
- **Fit Criterion（受入基準/検証）**: 鍵ローテーション記録が年1回以上存在すること、証明書更新が自動化され、期限切れアラートが30日前に発報することを確認する（検査＋テスト）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: セキュリティ／運用
- **Supporting Materials**: 鍵管理手順、証明書運用手順
- **Conflicts/Dependencies**: アプリ再起動/デプロイ手順（NFR-DEV-003/004）と調整が必要。
