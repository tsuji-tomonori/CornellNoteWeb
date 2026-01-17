# NFR-PRC-005 データ所在地（リージョン制約）

## Snow Card
- **Requirement ID**: NFR-PRC-005
- **Type**: Non-functional（Compliance/Data Residency）
- **Description（INCOSE形式）**: 本システムは、規制または契約で指定された場合、対象データを指定リージョン外へ保管または処理してはならない。
- **Rationale**: 越境移転規制・契約違反の回避のため。
- **Fit Criterion（受入基準/検証）**: 利用リージョンが設計/設定で固定され、バックアップ/DRを含む保管先が要件に適合していることを構成証跡で確認する（検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must（該当する場合）
- **Originator**: 法務／コンプライアンス
- **Supporting Materials**: 契約条項、規制一覧
- **Conflicts/Dependencies**: DR（NFR-AV-006）との両立が必要。
