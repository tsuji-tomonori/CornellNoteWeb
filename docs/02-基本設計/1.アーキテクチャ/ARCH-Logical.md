# アーキテクチャ概要（論理）

## 論理構成
```mermaid
flowchart TB
  subgraph Client
    web[Browser]
  end
  subgraph App
    app[Spring Boot App (SSR UI + API)]
    worker[Job Worker]
  end
  subgraph Data
    db[(Relational DB)]
    obj[(Object Storage)]
  end

  web --> app
  app --> db
  app --> obj
  worker --> db
  worker --> obj
```

## コンポーネント
- Spring Boot App: SSR UI（認証UI、ノートエディタ、一覧/検索、共有閲覧） + API（認証、CRUD、検索、共有リンク、エクスポート）
- Job Worker: PDF生成、メール送信キュー処理
- DB: メタデータ/権限/インデックス
- Object Storage: ノート本文、PDF、添付
