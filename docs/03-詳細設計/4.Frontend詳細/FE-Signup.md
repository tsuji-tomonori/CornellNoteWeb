# 新規登録画面（signup.html）仕様

## 概要
- メール/パスワードでアカウントを作成（モック）し、ログイン状態にする。

## 画面URL/権限
- URL: `/mock/signup.html`
- 権限: 未ログインのみ（ログイン済みは `notes.html` へリダイレクト）

## レイアウト/主要要素
- `main.auth-container` 内に `div.auth-card`
- フォーム
  - メールアドレス（`#email`）
  - パスワード（`#password`、8文字以上）
  - パスワード確認（`#confirmPassword`）
  - 作成ボタン（`#signupForm` submit）
- Google登録ボタン（`#googleSignup`）
- ログインリンク（`login.html`）
- ステータス領域（`#statusRegion`, `#alertRegion`）

## ユーザー操作
- 新規登録（フォーム送信）
  - バリデーションを通過したら `storage.setSession` でセッション保存
  - 成功トースト表示後、1秒後に `notes.html` へ遷移
- Google登録
  - デモユーザーでセッション保存
  - `notes.html` へ遷移

## バリデーション/エラーメッセージ
- パスワード不一致
  - トースト: 「パスワードが一致しません」
- パスワード8文字未満
  - トースト: 「パスワードは8文字以上で入力してください」

## 状態/表示分岐
- ログイン済み: 即時 `notes.html` へ遷移

## データ/外部I/F
- セッション保存: `localStorage` (`cornell_session`)

## API/エラーステータス
- 登録（モック）
  - 201: アカウント作成
- 想定エラー
  - 400: バリデーションエラー → 入力促し
  - 409: メール重複 → エラー通知
  - 500: 登録失敗 → エラー通知

## アクセシビリティ
- `role="status"`/`role="alert"` による通知
- スキップリンク、`aria-live` 対応
