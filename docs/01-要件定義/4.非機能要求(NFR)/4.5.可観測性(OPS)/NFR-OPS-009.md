# NFR-OPS-009 時刻同期（ログ整合性）

## Snow Card
- **Requirement ID**: NFR-OPS-009
- **Type**: Non-functional（Operability/Security）
- **Description（INCOSE形式）**: 本システムは、すべての計算ノードのシステム時刻を信頼できる時刻源に同期し、時刻ずれを1秒以内に維持しなければならない。
- **Rationale**: ログ相関、証明書検証、監査証跡の信頼性を担保するため。
- **Fit Criterion（受入基準/検証）**: 時刻同期設定が有効であり、時刻ずれが監視され、常時1秒以内であることを確認する（検査＋分析）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: 運用／SRE
- **Supporting Materials**: 運用標準
- **Conflicts/Dependencies**: 監査証跡（NFR-PRC-004）に関連。
