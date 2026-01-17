# 画面遷移

```mermaid
flowchart LR
  login[ログイン] --> list[ノート一覧]
  signup[サインアップ] --> list
  list --> editor[ノート編集]
  list --> settings[設定]
  editor --> list
  share[共有閲覧] -->|閲覧のみ| share
```

## 遷移方針
- 未認証はログイン/サインアップのみ
- 共有リンクは専用の閲覧画面に固定
- 設定から退会フローへ遷移
