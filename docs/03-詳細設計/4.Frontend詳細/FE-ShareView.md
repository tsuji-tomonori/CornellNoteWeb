# 共有ノート閲覧画面（share.html）仕様

## 概要
- 共有リンクで読み取り専用のノートを閲覧する。

## 画面URL/権限
- URL: `/share.html?token=<shareToken>`
- 権限: 未ログインでも閲覧可

## レイアウト/主要要素
- シンプルヘッダー（ロゴリンク）
- ローディング表示（`#loadingState`）
- エラー表示（`#errorState`）
- コンテンツ表示（`#noteContent`）
  - タイトル（`#noteTitle`）
  - 作成日（`#createdAt`）
  - Cue/Notes/Summary
- CTA（`signup.html`）

## ユーザー操作
- 共有リンクで閲覧
- CTAから新規登録へ遷移

## 状態/表示分岐
- `token` がない/取得失敗
  - エラー表示 + `aria-live` 通知
- 取得成功
  - ローディング非表示、ノート表示

## データ/外部I/F
- `api.getSharedNote(token)` -> `/api/share/<token>`

## API/エラーステータス
- 取得系
  - 200: 共有ノート表示
- 想定エラー
  - 404: リンク無効 → エラー表示
  - 410: 削除済み → エラー表示
  - 500: 取得失敗 → エラー表示

## アクセシビリティ
- エラーは `role="alert"` で通知
- スキップリンク、`aria-live` 対応
