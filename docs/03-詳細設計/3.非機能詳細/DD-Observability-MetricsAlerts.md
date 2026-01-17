# メトリクス/アラート設計（詳細）

## 対応NFR
- NFR-OPS-002, OPS-003, OPS-006, OPS-008
- NFR-PS-001, PS-003, PS-004
- NFR-AV-001

## 収集メトリクス（RED/USE）
- RED: request_rate, error_rate, duration_p95
- USE: cpu_util, memory_util, saturation, dependency_errors

## ダッシュボード
- SLO達成状況（月次/週次）
- エラーバジェット消費
- 主要UC別の遅延/エラー

## アラートルール
- 重大: SLO逸脱/予兆（ページャー）
- 中: エラー増、遅延増（チャット）
- 低: リソース増、コスト傾向（メール）

## 通知/エスカレーション（OPS-003）
- 通知経路（冗長化）
- 月次通知テスト手順
