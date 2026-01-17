# NFR-DATA-001 データ整合性（制約と検証）

## Snow Card
- **Requirement ID**: NFR-DATA-001
- **Type**: Non-functional（Data Integrity）
- **Description（INCOSE形式）**: 本システムは、永続データに対して、業務上必要な整合性制約（例：必須項目、参照整合性、ユニーク制約）を定義し、保存時に検証しなければならない。
- **Rationale**: 不正データの蓄積による障害・業務影響を防ぐため。
- **Fit Criterion（受入基準/検証）**: データモデルに制約が実装され、代表的な不正入力が保存できないことをテストで確認する（テスト＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: 開発／DBA
- **Supporting Materials**: データモデル定義、バリデーション仕様
- **Conflicts/Dependencies**: 性能（NFR-PS-007）とのトレードオフがあり得る。
