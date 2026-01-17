# NFR-PRC-002 PIIマスキング（非本番/ログ）

## Snow Card
- **Requirement ID**: NFR-PRC-002
- **Type**: Non-functional（Privacy）
- **Description（INCOSE形式）**: 本システムは、非本番環境およびログ出力において、個人情報または機密情報をマスキングまたはトークナイズしなければならない。
- **Rationale**: 開発・運用過程での漏えいを防止するため。
- **Fit Criterion（受入基準/検証）**: ログに生PIIが出力されないことをサンプリング検査で確認し、テストデータの生成手順が整備されていることを確認する（検査＋テスト）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: 法務／セキュリティ／開発
- **Supporting Materials**: ログ設計、テストデータ方針
- **Conflicts/Dependencies**: 障害解析（NFR-OPS-001）との両立設計が必要。
