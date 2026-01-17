# NFR-DATA-004 データ移行・マイグレーションの検証

## Snow Card
- **Requirement ID**: NFR-DATA-004
- **Type**: Non-functional（Data/Quality Assurance）
- **Description（INCOSE形式）**: 本システムは、データ移行またはスキーママイグレーションを実施する場合、移行前後での件数・整合性・主要集計値の一致を検証しなければならない。
- **Rationale**: 移行不備による業務影響を防ぐため。
- **Fit Criterion（受入基準/検証）**: 移行検証チェックリスト（件数、参照整合性、代表レポート値等）が作成され、移行ごとに結果が保存されていること（検査＋分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: 開発／DBA
- **Supporting Materials**: 移行計画、検証結果
- **Conflicts/Dependencies**: ロールバック（NFR-DEV-004）との整合が必要。
