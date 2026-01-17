# 入口/リダイレクト画面（index.html）仕様

## 概要
- ログイン状態を判定して適切な画面へ遷移するだけの入口ページ。

## 画面URL/権限
- URL: `/mock/index.html`
- 権限: なし（未ログインでもアクセス可）

## レイアウト/主要要素
- `main#mainContent` に「リダイレクト中...」の表示のみ
- スキップリンク（本文へスキップ）

## 処理フロー
- `DOMContentLoaded` 時に `CornellApp.storage.isLoggedIn()` を評価
  - true: `notes.html` へ遷移
  - false: `login.html` へ遷移

## 状態/表示分岐
- 実質的な表示は遷移までの待機のみ

## アクセシビリティ
- `lang=ja`、スキップリンク、`main` へのフォーカス移動に対応
