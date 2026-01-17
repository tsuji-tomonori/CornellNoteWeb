# NFR-SEC-002 特権アクセス強化（MFA/条件付きアクセス）

## Snow Card
- **Requirement ID**: NFR-SEC-002
- **Type**: Non-functional（Security）
- **Description（INCOSE形式）**: 本システムは、特権操作を行う利用者に対して、多要素認証および条件付きアクセス（端末/場所/時間等）を適用しなければならない。
- **Rationale**: 不正ログイン・なりすまし対策のため。
- **Fit Criterion（受入基準/検証）**: 特権ロールにMFAが必須であり、条件付きアクセスポリシーが有効であることを設定証跡で確認する（検査）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: セキュリティ／監査
- **Supporting Materials**: 認証ポリシー、IdP設定
- **Conflicts/Dependencies**: 運用利便性との調整が必要。
