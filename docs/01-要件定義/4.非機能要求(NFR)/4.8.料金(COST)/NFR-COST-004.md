# NFR-COST-004 コスト異常検知（Anomaly Detection）

## Snow Card
- **Requirement ID**: NFR-COST-004
- **Type**: Non-functional（Cost Monitoring）
- **Description（INCOSE形式）**: 本システムは、日次のクラウド利用費について、過去トレンドに対する異常増加を検知し、24時間以内に関係者へ通知しなければならない。
- **Rationale**: 構成ミスや暴走ジョブによるコスト事故を早期に検知するため。
- **Fit Criterion（受入基準/検証）**: 異常検知ルールが設定され、模擬的なコスト増加イベントで通知が24時間以内に到達することを確認する（テスト＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: FinOps／運用
- **Supporting Materials**: コスト監視設計
- **Conflicts/Dependencies**: タグ配賦（NFR-COST-001）が精度に影響。
