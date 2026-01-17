# ログ設計（詳細）

## 対応NFR
- NFR-OPS-001, NFR-OPS-009
- NFR-PRC-002, NFR-PRC-004
- NFR-SEC-008

## ログ形式
- JSON（構造化）
- timestamp: ISO8601, UTC（例: 2026-01-17T12:34:56.789Z）
- level: error/warn/info/debug
- 必須フィールド
  - service, env, version, host
  - traceId（W3C traceparent）/spanId
  - requestId, userId（可能なら。PIIは入れない/マスク）
  - eventType（監査イベント分類）
  - latencyMs, statusCode
- 1ログ最大サイズ: 10KB（超過時はstacktrace分割）

## ログレベル/サンプリング
- 本番: info以上、debugは無効
- 非本番: debug有効（24h限定）
- サンプリング
  - error: 100%
  - warn: 100%
  - info: 10%（リクエストログのみ）

## PII/機密マスキング（PRC-002）
- マスキング対象
  - email: a***@domain
  - 氏名: 先頭1文字のみ残す
  - 電話番号: 下4桁のみ
- 例外
  - 障害解析で必要な場合はマスキング解除は不可、代替としてハッシュ化のみ
  - 解除は不可（設計方針）

## 監査ログ（PRC-004）
- 監査イベント
  - ログイン/ログアウト/権限変更/データ削除/設定変更
- 保管方式
  - 監査専用ストレージ、削除権限制限（WORM相当）
- 保持期間: 365日以上

## 時刻同期（OPS-009）
- 全ノードでNTPを有効化
- 時刻ずれ: 1秒以内を維持（監視対象）

## 保持/検索性
- 主要ログ: 30日（運用用途）
- 監査ログ: 365日以上（監査用途）
- インデックス
  - traceId, requestId, userId, eventType, statusCode
- 監査ログの検索権限: セキュリティ担当のみ
