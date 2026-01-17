# ERD（概要）

```mermaid
erDiagram
  USER ||--o{ NOTEBOOK : owns
  NOTEBOOK ||--o{ NOTE : contains
  NOTE ||--o{ NOTE_TAG : tagged
  TAG ||--o{ NOTE_TAG : tagged
  NOTE ||--o{ SHARE_TOKEN : shared
  NOTE ||--o{ EXPORT_JOB : exported

  USER {
    string id PK
    string email
    string authProvider
    string status
    datetime createdAt
    datetime updatedAt
    datetime deletedAt
  }
  NOTEBOOK {
    string id PK
    string userId FK
    string name
    int sortOrder
    datetime createdAt
    datetime updatedAt
    datetime deletedAt
  }
  NOTE {
    string id PK
    string notebookId FK
    string title
    string contentRef
    string summaryRef
    string cueRef
    string status
    datetime createdAt
    datetime updatedAt
    datetime deletedAt
  }
  TAG {
    string id PK
    string userId FK
    string name
    datetime createdAt
    datetime updatedAt
  }
  %% TAG: UNIQUE(userId, name)
  NOTE_TAG {
    string noteId PK, FK
    string tagId PK, FK
  }
  SHARE_TOKEN {
    string id PK
    string noteId FK
    string tokenHash
    string status
    datetime expiresAt
    datetime createdAt
    datetime revokedAt
  }
  EXPORT_JOB {
    string id PK
    string noteId FK
    string status
    string outputRef
    string errorMessage
    datetime createdAt
    datetime completedAt
  }
```

## 状態
- Note.status: draft/active/archived
- ShareToken.status: active/revoked/expired
- ExportJob.status: queued/running/succeeded/failed

## 補足
- contentRef/summaryRef/cueRef はObject Storage参照
- errorMessageは失敗時のみ保存
- NOTE_TAG は noteId + tagId の一意制約を持つ
- TAG は userId + name の一意制約を持つ（同一ユーザー内で同名タグは不可）
