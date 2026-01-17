# NFR-PRC-003 データ保持と削除（自動化）

## Snow Card
- **Requirement ID**: NFR-PRC-003
- **Type**: Non-functional（Compliance/Records Management）
- **Description（INCOSE形式）**: 本システムは、データの保持期間および削除要件に従い、期限到来データを自動的に削除または匿名化しなければならない。
- **Rationale**: 過剰保持によるリスクとコストを削減するため。
- **Fit Criterion（受入基準/検証）**: 保持ルールが設定され、期限到来データが所定のジョブで処理されることを監査ログで確認できること（分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: 法務／コンプライアンス
- **Supporting Materials**: 保持ポリシー、データフロー図
- **Conflicts/Dependencies**: 監査証跡保持（NFR-PRC-004）との整合が必要。
