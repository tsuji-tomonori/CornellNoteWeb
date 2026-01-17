# CornellNoteWeb

Cornell ノート式の学習メモを Web で作成・整理するためのプロジェクトです。

## セットアップ

事前に pre-commit を有効化してください。

```sh
task precommit
```

## Docker

Docker でアプリを起動する場合は以下を実行します。

```sh
task docker:build
task docker:up
```

起動後は `http://localhost:13000` でアクセスできます。
