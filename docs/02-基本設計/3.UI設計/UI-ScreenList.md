# 画面一覧

| 画面 | 用途 | 認証 | 備考 |
| --- | --- | --- | --- |
| ログイン | 認証 | 不要 | Google OAuth対応（PC-005） |
| サインアップ | 新規登録 | 不要 | メール認証含む |
| ノート一覧 | ノート閲覧/検索 | 必要 | MVP対象（PC-007） |
| ノート編集 | Cornell編集 | 必要 | 自動保存（FR-017） |
| 共有閲覧 | 閲覧専用 | 不要 | 編集不可（PC-004） |
| 設定 | プロフィール/退会 | 必要 | FR-060 |

## 主要コンポーネント
- Global Header（検索、ユーザー）
- Sidebar（Notebook/Tag）
- Editor（Cue/Notes/Summary）
- Status Indicator（保存済み/未保存/失敗）
