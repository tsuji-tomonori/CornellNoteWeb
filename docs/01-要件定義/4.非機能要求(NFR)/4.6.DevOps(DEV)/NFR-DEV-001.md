# NFR-DEV-001 Infrastructure as Code（IaC）

## Snow Card
- **Requirement ID**: NFR-DEV-001
- **Type**: Non-functional（Maintainability/Operability）
- **Description（INCOSE形式）**: 本システムは、クラウドリソースおよび設定をInfrastructure as Codeとして定義し、再現可能な手順で環境を構築しなければならない。
- **Rationale**: 構成ドリフト防止、再現性、監査容易性のため。
- **Fit Criterion（受入基準/検証）**: 本番相当環境がIaCのみで新規構築でき、手作業変更が検知・是正される運用が定義されていることを確認する（実演＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: プラットフォーム／運用
- **Supporting Materials**: IaC標準、リポジトリ
- **Conflicts/Dependencies**: 構成コンプライアンス（NFR-SEC-009）と整合が必要。
