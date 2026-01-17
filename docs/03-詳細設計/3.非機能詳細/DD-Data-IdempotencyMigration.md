# 冪等性・重複排除・移行検証（詳細）

## 対応NFR
NFR-DATA-001〜005

## API冪等性（DATA-002）
- 対象API
  - POST /notes
  - POST /notes/{id}/share
  - POST /notes/export
- 冪等キー仕様
  - ヘッダ: Idempotency-Key
  - 形式: UUID v4（36文字）
  - 有効期限: 24時間
  - 保存先: idempotency_keys テーブル
  - 保存内容: request_hash, response_body, status_code, created_at, expires_at
- 衝突時の挙動
  - 同一キー+同一payload: 以前の結果を返却
  - 同一キー+異なるpayload: 409 Conflict
- リトライ標準（AV-008整合）
  - リトライ回数: 3回
  - バックオフ: 0.5s, 1s, 2s

## メッセージ重複排除（DATA-003）
- 重複排除キー: message_id（プロデューサがUUID付与）
- 処理済み記録テーブル
  - processed_messages: message_id, processed_at, handler
  - 保持期間: 7日
- 再処理戦略
  - 期限内は再処理せずACK
  - 期限切れは再処理可

## 移行検証（DATA-004）
- チェックリスト
  - 件数一致（差分0.1%以内）
  - 参照整合性（外部キーエラー 0件）
  - 主要集計（ノート数/共有数/エクスポート数）
  - ランダムサンプル 200件の内容一致
- ロールバック（DEV-004）との整合
  - 切戻し条件: 差分0.5%以上またはFKエラー>0
- 結果保存（PRC-008）
  - 検証結果をレポートとして保存
