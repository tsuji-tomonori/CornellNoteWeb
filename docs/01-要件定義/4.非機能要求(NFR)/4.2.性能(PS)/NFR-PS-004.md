# NFR-PS-004 オートスケーリング（自動拡張）

## Snow Card
- **Requirement ID**: NFR-PS-004
- **Type**: Non-functional（Scalability）
- **Description（INCOSE形式）**: 本システムは、負荷増大を検知した場合に、5分以内に処理能力を自動拡張し、性能要件（NFR-PS-001）を満たし続けなければならない。
- **Rationale**: 手動対応による遅れを防止するため。
- **Fit Criterion（受入基準/検証）**: スケールアウトのトリガ・上限・検証手順が定義され、ピーク想定の負荷試験で5分以内に安定することを確認する（テスト＋分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: SRE
- **Supporting Materials**: スケーリング設計、試験結果
- **Conflicts/Dependencies**: コスト（NFR-COST-002）とトレードオフ。
