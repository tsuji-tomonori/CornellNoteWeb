# NFR-SEC-005 保存データの暗号化

## Snow Card
- **Requirement ID**: NFR-SEC-005
- **Type**: Non-functional（Security/Crypto）
- **Description（INCOSE形式）**: 本システムは、永続化される機密データを保管時に暗号化しなければならない。
- **Rationale**: ストレージ漏えい時の被害抑制のため。
- **Fit Criterion（受入基準/検証）**: データ分類（NFR-PRC-001）に基づき、対象ストレージで暗号化が有効であること、および鍵のアクセス制御が最小権限であることを確認する（検査）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: セキュリティ／監査
- **Supporting Materials**: データ分類、鍵管理手順
- **Conflicts/Dependencies**: 性能要件（NFR-PS-*）との調整が必要。
