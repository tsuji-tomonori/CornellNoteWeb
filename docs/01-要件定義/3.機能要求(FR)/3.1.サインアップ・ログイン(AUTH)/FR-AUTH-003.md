# FR-AUTH-003 パスワードリセットを要求できる

## Snow Card
- **Requirement ID**: FR-AUTH-003
- **Type**: Functional
- **Use Case**: UC-01
- **Description（INCOSE形式）**: 本システムは、利用者がパスワードリセット手続きを開始できるようにしなければならない。
- **Rationale**: ログイン不能時に自己復旧手段がないと離脱・問い合わせ増加につながるため。
- **Fit Criterion（受入基準/検証）**: 利用者が登録済みメールアドレスを入力したとき、リセット用メールが送信され、リンクから新しいパスワードを設定できることを確認する（テスト）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: 運用担当
- **Supporting Materials**: UC-01、メール文面案
- **Conflicts/Dependencies**: 外部メール送信（PC-006）に依存。
