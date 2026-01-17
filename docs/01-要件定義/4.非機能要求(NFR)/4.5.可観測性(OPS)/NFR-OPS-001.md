# NFR-OPS-001 集中ログと相関ID

## Snow Card
- **Requirement ID**: NFR-OPS-001
- **Type**: Non-functional（Operability/Observability）
- **Description（INCOSE形式）**: 本システムは、アプリケーションおよび基盤のログを集中管理し、要求単位で追跡可能な相関IDをログに含めなければならない。
- **Rationale**: 障害解析・監査・セキュリティ調査を迅速化するため。
- **Fit Criterion（受入基準/検証）**: ログが集中基盤へ転送され、主要API呼出に相関IDが付与されていることをサンプルログで確認する（検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: 運用／開発
- **Supporting Materials**: ログ設計、ログ項目定義
- **Conflicts/Dependencies**: PIIマスキング（NFR-PRC-002）と整合が必要。
