# NFR-DATA-003 メッセージ処理の重複排除（At-least-once対応）

## Snow Card
- **Requirement ID**: NFR-DATA-003
- **Type**: Non-functional（Reliability/Data）
- **Description（INCOSE形式）**: 本システムは、メッセージングやイベント処理において、少なくとも一度の配送が発生しても、重複処理により業務データが不整合にならないようにしなければならない。
- **Rationale**: 分散システムで一般的な重複配送による障害を防ぐため。
- **Fit Criterion（受入基準/検証）**: 重複排除キー、処理済み記録、または冪等処理が実装され、同一イベントを重複投入しても結果が正しいことをテストで確認する（テスト）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Should
- **Originator**: アーキテクト／開発
- **Supporting Materials**: イベント設計、冪等設計
- **Conflicts/Dependencies**: データ整合性（NFR-DATA-001）と整合が必要。
