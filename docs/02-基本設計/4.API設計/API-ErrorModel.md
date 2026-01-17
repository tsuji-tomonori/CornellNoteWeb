# エラー体系

## 方針
- HTTPステータス + エラーコード
- 利用者向けメッセージはNFR-UX-001に準拠

## 例
| HTTP | Code | 説明 |
| --- | --- | --- |
| 400 | BAD_REQUEST | 入力不正 |
| 401 | UNAUTHORIZED | 未認証 |
| 403 | FORBIDDEN | 権限不足 |
| 404 | NOT_FOUND | リソースなし |
| 409 | CONFLICT | 競合 |
| 429 | RATE_LIMITED | レート制限 |
| 500 | INTERNAL_ERROR | 予期しない障害 |

## レスポンス例
```json
{
  "code": "BAD_REQUEST",
  "message": "入力内容に誤りがあります",
  "traceId": "..."
}
```
