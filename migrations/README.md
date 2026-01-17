# マイグレーション

このディレクトリには、dbmate 用の PostgreSQL DDL マイグレーションを配置しています。

## 前提

- dbmate をインストール: https://github.com/amacneil/dbmate
- PostgreSQL の接続情報を `DATABASE_URL` に設定

例:

```bash
export DATABASE_URL="postgres://user:pass@localhost:5432/dbname?sslmode=disable"
```

## マイグレーション適用

```bash
dbmate --migrations-dir "./migrations" up
```

## ステータス確認

```bash
dbmate --migrations-dir "./migrations" status
```

## 直近のマイグレーションを戻す

```bash
dbmate --migrations-dir "./migrations" down
```

## Taskfile ショートカット

```bash
task db:up
task db:status
task db:down
```
