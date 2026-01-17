# NFR-DATA-005 監査フィールドと変更履歴

## Snow Card
- **Requirement ID**: NFR-DATA-005
- **Type**: Non-functional（Auditability/Data）
- **Description（INCOSE形式）**: 本システムは、業務上重要なデータに対して、作成日時、更新日時、作成者、更新者等の監査フィールドを保持し、必要に応じて変更履歴を追跡できるようにしなければならない。
- **Rationale**: 不正調査、障害解析、説明責任を果たすため。
- **Fit Criterion（受入基準/検証）**: 主要テーブル/エンティティに監査フィールドが存在し、更新時に正しく更新されることをテストで確認する（テスト＋検査）。変更履歴が必要なデータは対象一覧に明記する（検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: 監査／開発
- **Supporting Materials**: データ台帳、監査要件
- **Conflicts/Dependencies**: 個人情報削除（NFR-PRC-003）との整合が必要。
