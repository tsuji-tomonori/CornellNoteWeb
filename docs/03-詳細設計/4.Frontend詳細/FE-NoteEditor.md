# ノート編集画面（editor.html）仕様

## 概要
- Cornellノート形式で編集し、自動保存、共有リンク生成、PDF出力、削除を行う。

## 画面URL/権限
- URL: `/mock/editor.html?id=<noteId|new>`
- 権限: ログイン必須（未ログインは `login.html` へ遷移）

## レイアウト/主要要素
- ヘッダー
  - 保存状態（`.save-status`）
  - 共有ボタン（`#shareBtn`）
  - PDF出力（`#exportBtn`）
  - 削除（`#deleteBtn`）
- エディタ
  - タイトル（`#noteTitle`）
  - ノートブック選択（`#notebookSelect`）
  - Cue（`#cueArea`）
  - Notes（`#notesArea`）
  - Summary（`#summaryArea`）
  - タグ一覧（`#tagContainer`）
  - タグ入力（`#tagInput`）
- 共有モーダル（`#shareModal`）
- 削除モーダル（`#deleteModal`）

## ユーザー操作
- 入力
  - タイトル/本文/要約/ノートブック変更で自動保存トリガ
- タグ追加
  - `Enter` でタグ作成、重複は無視
- タグ削除
  - タグ内の `×` クリックで削除
- 共有
  - モーダル表示 → リンク生成 → コピー
- PDF出力
  - トーストで進捗/完了を通知
- 削除
  - 確認モーダルで削除 → `notes.html` へ遷移

## 状態/表示分岐
- `id` が `new`
  - 空のノート状態を生成
- 既存ノート
  - `api.getNote` で取得しフォームに反映
- 自動保存
  - `saving`/`saved`/`error` に応じて表示更新

## データ/外部I/F
- `api.getNotebooks()` -> `/mock/api/notebooks.json`
- `api.getNote(id)` -> `/mock/api/note-XXX.json`
- 共有リンク: `share.html?token=abc123`

## API/エラーステータス
- 取得系
  - Notebook一覧/Note取得: 200で表示
- 更新系（モック）
  - 保存/削除/共有はUI上の疑似処理
- 想定エラー
  - 401: セッション無効 → `login.html` へ遷移
  - 404: ノート不在 → 空フォームのまま + 通知
  - 500: 取得失敗 → トースト/通知

## バリデーション/エラーメッセージ
- 本モックでは必須入力/保存APIは未実装
- 保存失敗時は `保存エラー` 表示（`AutoSave`）

## アクセシビリティ
- モーダルはフォーカストラップ/ESCで閉じる
- `aria-live` による保存状態通知
- スキップリンク、`label` によるフォーム関連付け
