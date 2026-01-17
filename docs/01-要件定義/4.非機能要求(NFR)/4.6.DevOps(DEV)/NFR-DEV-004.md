# NFR-DEV-004 迅速ロールバック

## Snow Card
- **Requirement ID**: NFR-DEV-004
- **Type**: Non-functional（Recoverability）
- **Description（INCOSE形式）**: 本システムは、リリース後に重大な不具合が検知された場合に、30分以内に直前の安定版へロールバックできなければならない。
- **Rationale**: 障害時間を短縮するため。
- **Fit Criterion（受入基準/検証）**: ロールバック手順が定義され、四半期に1回以上の訓練で30分以内に完了することを確認する（テスト＋分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: 運用／SRE
- **Supporting Materials**: 運用手順、訓練記録
- **Conflicts/Dependencies**: DBスキーマ互換（NFR-DEV-005）と整合が必要。
