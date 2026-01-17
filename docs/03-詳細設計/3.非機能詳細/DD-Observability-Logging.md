# ログ設計（詳細）

## 対応NFR
- NFR-OPS-001, NFR-OPS-009
- NFR-PRC-002, NFR-PRC-004
- NFR-SEC-008

## ログ形式
- JSON（構造化）
- 必須フィールド例:
  - timestamp, level, service, env
  - traceId（相関ID）, requestId
  - userId（可能なら。PIIは入れない/マスク）
  - eventType（監査イベント分類）
  - latencyMs, statusCode

## PII/機密マスキング（PRC-002）
- マスキング対象一覧（メール、氏名等）
- 例外（障害解析で必要な場合）と承認フロー

## 監査ログ（PRC-004）
- 監査イベント一覧
- 改ざん困難な保管方式（削除権限制限、Write Once相当）
- 保持期間: 1年以上

## 保持/検索性
- 主要ログ: 30日（運用用途）
- 監査ログ: 1年以上（監査用途）
- インデックス戦略（traceId検索、userId検索など）
