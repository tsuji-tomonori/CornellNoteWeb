# NFR-DATA-002 APIの冪等性（再送耐性）

## Snow Card
- **Requirement ID**: NFR-DATA-002
- **Type**: Non-functional（Reliability/Data）
- **Description（INCOSE形式）**: 本システムは、外部から再送が発生し得る更新系APIについて、冪等性を提供し、同一要求の重複処理を防止しなければならない。
- **Rationale**: ネットワーク再送やタイムアウト再試行による二重登録を防ぐため。
- **Fit Criterion（受入基準/検証）**: 冪等キーまたは等価な仕組みが実装され、同一要求を複数回送信しても結果が一意であることをテストで確認する（テスト）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Should
- **Originator**: アーキテクト／開発
- **Supporting Materials**: API設計標準
- **Conflicts/Dependencies**: レート制御（NFR-PS-005）・リトライ（NFR-AV-008）と関連。
