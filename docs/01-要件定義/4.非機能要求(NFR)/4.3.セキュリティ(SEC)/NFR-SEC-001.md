# NFR-SEC-001 最小権限（Least Privilege）

## Snow Card
- **Requirement ID**: NFR-SEC-001
- **Type**: Non-functional（Security/IAM）
- **Description（INCOSE形式）**: 本システムは、利用者・サービスアカウント・運用者の権限を最小権限で付与しなければならない。
- **Rationale**: 侵害時の被害範囲を最小化するため。
- **Fit Criterion（受入基準/検証）**: 権限設計（ロール/ポリシー）がレビューされ、管理者相当の過剰権限が例外承認なしに付与されていないことを確認する（検査）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: セキュリティ
- **Supporting Materials**: IAM設計、例外申請フロー
- **Conflicts/Dependencies**: 緊急対応時の運用性とトレードオフ。
