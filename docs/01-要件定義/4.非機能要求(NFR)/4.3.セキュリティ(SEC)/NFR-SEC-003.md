# NFR-SEC-003 ネットワーク境界防御（到達性の最小化）

## Snow Card
- **Requirement ID**: NFR-SEC-003
- **Type**: Non-functional（Security/Network）
- **Description（INCOSE形式）**: 本システムは、本番環境において、管理系インタフェースおよびデータストアへの通信を、承認されたネットワーク経路に限定しなければならない。
- **Rationale**: 不正到達性を低下させるため。
- **Fit Criterion（受入基準/検証）**: インターネット到達性の有無、許可CIDR/経路、ファイアウォール設定が設計どおりであることを確認する（検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: セキュリティ／プラットフォーム
- **Supporting Materials**: ネットワーク設計、構成図
- **Conflicts/Dependencies**: 運用アクセス手段（踏み台等）と整合が必要。
