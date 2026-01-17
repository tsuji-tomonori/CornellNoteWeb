# FR-AUTH-002 メールアドレスでログインできる

## Snow Card
- **Requirement ID**: FR-AUTH-002
- **Type**: Functional
- **Use Case**: UC-01
- **Description（INCOSE形式）**: 本システムは、登録済み利用者がメールアドレスとパスワードでログインできるようにしなければならない。
- **Rationale**: 継続利用の入口として必須であるため。
- **Fit Criterion（受入基準/検証）**: 登録済みアカウントでログイン操作を行ったとき、認証に成功しノート一覧に遷移することを確認する（テスト）。誤ったパスワードの場合は認証失敗となることを確認する。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: エンドユーザー代表
- **Supporting Materials**: UC-01、認証フロー図
- **Conflicts/Dependencies**: FR-AUTH-001に依存。
