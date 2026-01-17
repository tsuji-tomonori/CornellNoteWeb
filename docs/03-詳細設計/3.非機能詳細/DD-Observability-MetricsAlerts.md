# メトリクス/アラート設計（詳細）

## 対応NFR
- NFR-OPS-002, OPS-003, OPS-006, OPS-008
- NFR-PS-001, PS-003, PS-004
- NFR-AV-001

## 収集メトリクス（RED/USE）
- RED
  - request_rate: rps
  - error_rate: 5xx+timeout比率
  - duration_p95/p99: ms
- USE
  - cpu_util: %
  - memory_util: %
  - saturation: スレッド/コネクション利用率
  - dependency_errors: 外部依存のエラー率
- 保持期間
  - 30日以上（NFR-OPS-006）

## ダッシュボード
- SLO達成状況（月次/週次）
- エラーバジェット消費
- 主要UC別の遅延/エラー（検索/保存/共有/エクスポート）
- リソース利用率（CPU/Memory/DB/Cache）

## アラートルール（具体値）
- 重大（ページャー）
  - 5xx+timeout >= 1.0% が5分継続
  - p95 latency >= 600ms が5分継続
  - 可用性 99.9% を日次で下回る
- 中（チャット）
  - 5xx+timeout >= 0.5% が10分継続
  - p95 latency >= 400ms が10分継続
  - CPU >= 80% が10分継続
- 低（メール）
  - エラーバジェット消費 30% 到達
  - コスト 7日平均比 150% 超（NFR-COST-004）

## 通知/エスカレーション（OPS-003）
- 通知経路: Pager（一次）/Slack（全員）/Email（管理）
- エスカレーション
  - 15分無応答で二次担当
  - 30分無応答で管理者
- 月次通知テスト: 毎月第1営業日に擬似アラート送信
