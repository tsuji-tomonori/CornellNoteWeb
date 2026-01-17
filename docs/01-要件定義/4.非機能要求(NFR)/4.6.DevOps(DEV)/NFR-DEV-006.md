# NFR-DEV-006 自動テストの網羅（回帰防止）

## Snow Card
- **Requirement ID**: NFR-DEV-006
- **Type**: Non-functional（Quality）
- **Description（INCOSE形式）**: 本システムは、主要ユースケースに対する自動テスト（単体・結合・E2E）を整備し、CIにおいてテストが成功しなければ変更をマージしてはならない。
- **Rationale**: 回帰バグの早期検出と品質維持のため。
- **Fit Criterion（受入基準/検証）**: 主要ユースケース一覧に対してE2Eテストが紐付いていること、CIでテストが必須ゲートとなっていることを確認する（検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: 開発／QA
- **Supporting Materials**: テスト方針、テストケース一覧
- **Conflicts/Dependencies**: リリース頻度と実行時間の調整が必要。
