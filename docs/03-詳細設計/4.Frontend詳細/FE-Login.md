# ログイン画面（login.html）仕様

## 概要
- メール/パスワード、またはGoogleログイン（モック）でセッションを作成する。

## 画面URL/権限
- URL: `/mock/login.html`
- 権限: 未ログインのみ（ログイン済みは `notes.html` へリダイレクト）

## レイアウト/主要要素
- `main.auth-container` 内に `div.auth-card`
- フォーム
  - メールアドレス（`#email`）
  - パスワード（`#password`）
  - ログインボタン（`#loginForm` submit）
- Googleログインボタン（`#googleLogin`）
- 新規登録リンク（`signup.html`）
- パスワード再設定リンク（ダミー）
- ステータス領域（`#statusRegion`, `#alertRegion`）

## ユーザー操作
- ログイン（フォーム送信）
  - 入力値が存在する場合、`storage.setSession` でセッション保存
  - `notes.html` へ遷移
- Googleログイン（ボタンクリック）
  - デモユーザーでセッション保存
  - `notes.html` へ遷移

## バリデーション/エラーメッセージ
- メール/パスワード未入力時
  - トースト: 「メールアドレスとパスワードを入力してください」
  - ステータス通知（alert）

## 状態/表示分岐
- ログイン済み: `DOMContentLoaded` で `notes.html` へ遷移

## データ/外部I/F
- セッション保存: `localStorage` (`cornell_session`)

## API/エラーステータス
- 認証（モック）
  - 200: セッション作成
- 想定エラー
  - 401: 認証失敗 → エラー通知
  - 429: レート制限 → 再試行案内
  - 500: 認証失敗 → エラー通知

## アクセシビリティ
- `role="status"`/`role="alert"` による通知
- スキップリンク、`aria-live` 対応
