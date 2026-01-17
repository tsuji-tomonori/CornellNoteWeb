# NFR-PS-003 スループット（ピーク処理能力）

## Snow Card
- **Requirement ID**: NFR-PS-003
- **Type**: Non-functional（Capacity）
- **Description（INCOSE形式）**: 本システムは、ピーク時において、主要APIを毎秒200リクエストの処理能力で提供しなければならない。
- **Rationale**: ピークアクセス時の業務停止を回避するため。
- **Fit Criterion（受入基準/検証）**: 負荷試験により、性能要件（NFR-PS-001）を維持したまま200rpsを処理できることを確認する（テスト）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: プロダクト／SRE
- **Supporting Materials**: 負荷試験計画、キャパシティモデル
- **Conflicts/Dependencies**: オートスケール（NFR-PS-004）に依存。
