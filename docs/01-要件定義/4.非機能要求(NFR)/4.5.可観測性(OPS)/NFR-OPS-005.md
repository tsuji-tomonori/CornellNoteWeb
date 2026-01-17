# NFR-OPS-005 ヘルスチェック（Liveness/Readiness）

## Snow Card
- **Requirement ID**: NFR-OPS-005
- **Type**: Non-functional（Reliability/Operability）
- **Description（INCOSE形式）**: 本システムは、稼働状態を判定するヘルスチェックを提供し、異常時に自動復旧または切離しが可能な状態を維持しなければならない。
- **Rationale**: 自動回復とフェイルオーバ判断の精度を向上させるため。
- **Fit Criterion（受入基準/検証）**: ヘルスチェックがLivenessとReadinessを区別して提供され、障害時に期待どおりの状態遷移となることを確認する（テスト）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: SRE／開発
- **Supporting Materials**: ヘルスチェック仕様
- **Conflicts/Dependencies**: 自動フェイルオーバ（NFR-AV-003）と整合が必要。
