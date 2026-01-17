# NFR-OPS-002 SLI/SLOの監視と可視化

## Snow Card
- **Requirement ID**: NFR-OPS-002
- **Type**: Non-functional（SRE/Monitoring）
- **Description（INCOSE形式）**: 本システムは、可用性・遅延・エラー率・スループットのSLIを計測し、SLOに対する達成状況を可視化しなければならない。
- **Rationale**: 運用判断と改善をデータ駆動にするため。
- **Fit Criterion（受入基準/検証）**: ダッシュボードでSLO達成が閲覧でき、指標の定義（計測対象・除外条件）が文書化されていることを確認する（検査＋分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: SRE
- **Supporting Materials**: SLO定義書、監視設計
- **Conflicts/Dependencies**: 性能要件（NFR-PS-001等）と整合が必要。
