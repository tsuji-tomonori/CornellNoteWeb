# ドメイン/データ設計（概念）

## 主要エンティティ
- User: アカウント、認証情報、状態
- Notebook: ノートのグループ
- Note: Cornellノート本体
- Tag: 任意タグ
- NoteTag: NoteとTagの多対多
- ShareToken: 共有リンク用トークン
- ExportJob: PDF生成ジョブ

## 属性方針
- ID: すべてUUID/ULIDなど推測困難なIDを採用
- timestamps: createdAt/updatedAt を保持
- 論理削除: deletedAt を持ち、FR-ACCOUNT-001/NFR-PRC-003と整合
- Tag: 同一ユーザー内で同名タグは作成不可（userId + name を一意制約）

## リレーション概要
- User 1..* Notebook
- Notebook 1..* Note
- Note *..* Tag（NoteTag経由）
- Note 0..* ShareToken
- Note 0..* ExportJob
