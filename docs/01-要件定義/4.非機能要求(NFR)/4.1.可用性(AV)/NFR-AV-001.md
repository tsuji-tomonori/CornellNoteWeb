# NFR-AV-001 可用性SLO

## Snow Card
- **Requirement ID**: NFR-AV-001
- **Type**: Non-functional（Availability）
- **Description（INCOSE形式）**: 本システムは、運用時間（24時間×365日）において、月次のサービス可用性を99.9%以上に維持しなければならない。
- **Rationale**: 事業継続性を確保し、停止による機会損失および信用低下を最小化するため。
- **Fit Criterion（受入基準/検証）**: 監視基盤で算出する可用性（(総時間−ダウン時間)/総時間）が月次で99.9%以上であること。ダウン時間の定義（主要機能が利用不能）はSLO定義書に明記し、月次レポートで確認する（分析＋検査）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: 事業部門／SRE
- **Supporting Materials**: SLO定義書、SLA案
- **Conflicts/Dependencies**: 監視要件（NFR-OPS-002）に依存。SLO値は事業影響分析により見直しの可能性。
