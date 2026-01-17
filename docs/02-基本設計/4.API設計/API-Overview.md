# API設計（概要）

## エンドポイント一覧
- Auth
  - POST /auth/login
  - POST /auth/logout
  - POST /auth/signup
  - POST /auth/password/reset
  - DELETE /account
- Notes
  - GET /notes
  - POST /notes
  - GET /notes/{id}
  - PUT /notes/{id}
  - DELETE /notes/{id}
  - GET /notes/{id}/content
- Search
  - GET /search?q=...&tag=...&notebookId=...
- Export
  - POST /notes/{id}/export/pdf
  - GET /exports/{id}
- Share
  - POST /notes/{id}/share
  - GET /share/{token}
  - DELETE /share/{token}

## 共通方針
- Spring Boot同一アプリ内でSSR UIとAPIを提供（HTTP API経由の公開）
- JSON/UTF-8
- ページング: limit/offset
- 監査ログの相関IDをヘッダーで付与
- エラーコード体系は `docs/02-基本設計/4.API設計/API-ErrorModel.md` に準拠
