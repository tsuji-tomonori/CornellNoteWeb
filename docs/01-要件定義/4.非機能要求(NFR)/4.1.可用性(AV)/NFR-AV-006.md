# NFR-AV-006 災害復旧（RTO/RPO）

## Snow Card
- **Requirement ID**: NFR-AV-006
- **Type**: Non-functional（Disaster Recovery）
- **Description（INCOSE形式）**: 本システムは、災害または広域障害時に、RTO 4時間以内およびRPO 15分以内でサービスを復旧しなければならない。
- **Rationale**: 重大障害時でも事業継続を可能にするため。
- **Fit Criterion（受入基準/検証）**: 年2回以上のDRテストを実施し、実測でRTO/RPOを満たすことをテストレポートとして保管する（テスト＋分析）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: 事業部門／BCP担当／SRE
- **Supporting Materials**: BCP、DR設計、テスト計画
- **Conflicts/Dependencies**: データ所在地（NFR-PRC-005）およびコスト（NFR-COST-*）とトレードオフ。
