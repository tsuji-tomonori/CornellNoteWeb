# NFR-SEC-009 セキュリティベースライン準拠（構成コンプライアンス）

## Snow Card
- **Requirement ID**: NFR-SEC-009
- **Type**: Non-functional（Security/Governance）
- **Description（INCOSE形式）**: 本システムは、クラウドリソースの設定が合意したセキュリティベースラインに準拠していることを継続的に評価し、逸脱を検知した場合は是正できなければならない。
- **Rationale**: 設定不備（公開ストレージ、過剰ポート開放等）によるインシデントを予防するため。
- **Fit Criterion（受入基準/検証）**: ベースライン（例：公開禁止、暗号化必須、ログ有効等）がポリシーとして管理され、日次で評価され、逸脱が検出された場合にチケットまたは自動是正が実施されることを確認する（検査＋分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: セキュリティ／プラットフォーム
- **Supporting Materials**: セキュリティベースライン、ポリシー定義
- **Conflicts/Dependencies**: IaC運用（NFR-DEV-001）と整合が必要。
