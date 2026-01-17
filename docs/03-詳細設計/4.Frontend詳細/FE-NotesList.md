# ノート一覧画面（notes.html）仕様

## 概要
- ノート一覧の表示/検索/フィルタ、新規作成への導線を提供する。

## 画面URL/権限
- URL: `/mock/notes.html`
- 権限: ログイン必須（未ログインは `login.html` へ遷移）

## レイアウト/主要要素
- ヘッダー
  - ロゴリンク（`notes.html`）
  - 検索フォーム（`#searchInput`）
  - ユーザーメニュー（`#userMenu`）
- サイドバー
  - 新規ノートボタン（`#newNoteBtn`）
  - ノートブック一覧（`#notebookList`）
  - タグ一覧（`#tagList`）
  - 設定リンク、ログアウト（`#logoutBtn`）
- メイン
  - タイトル（`#pageTitle`）
  - 件数（`#noteCount`）
  - ノートカード一覧（`#noteGrid`）
  - 空状態（`#emptyState`）
- ユーザーメニュードロップダウン（`#userDropdown`）

## ユーザー操作
- 新規ノート
  - `editor.html?id=new` へ遷移
- ノートカード選択
  - `editor.html?id=<noteId>` へ遷移
- 検索
  - 入力値でノートカードをクライアントフィルタ
- フィルタ
  - ノートブック/タグクリックで一覧を絞り込み
  - タイトルを対象名に更新
- ユーザーメニュー
  - クリックでドロップダウン表示/非表示
- ログアウト
  - `logout()` 実行でセッション破棄

## 状態/表示分岐
- ノート0件
  - `#noteGrid` 非表示、`#emptyState` 表示
- フィルタ結果0件
  - 空状態表示 + ステータス通知

## データ/外部I/F
- `api.getNotebooks()` -> `/mock/api/notebooks.json`
- `api.getTags()` -> `/mock/api/tags.json`
- `api.getNotes()` -> `/mock/api/notes.json`
- セッション: `localStorage` (`cornell_session`)

## API/エラーステータス
- 取得系
  - Notebooks/Tags/Notes: 200で一覧取得
- 想定エラー
  - 401: セッション無効 → `login.html` へ遷移
  - 404: データなし → 空状態表示
  - 500: 取得失敗 → 空状態表示 + ステータス通知

## アクセシビリティ
- ノートカードは `button` でフォーカス可能
- `Enter`/`Space` で遷移
- スキップリンク、`aria-live` ステータス通知
