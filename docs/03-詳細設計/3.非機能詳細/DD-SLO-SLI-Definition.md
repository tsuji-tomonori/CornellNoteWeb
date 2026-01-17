# SLO/SLI定義（詳細）

## 対応NFR
- NFR-AV-001, NFR-OPS-002, NFR-OPS-008
- NFR-PS-001, NFR-PS-002, NFR-PS-003

## SLO一覧（例）
- 可用性SLO（月次）: 99.9%
- API遅延SLO: 主要API p95 server time <= 300ms
- 体感SLO: 主要操作 p95 <= 2.0s
- エラー率SLO: 主要APIの5xx比率 <= X%

## SLI定義（例：API成功率）
- 成功 = HTTP 2xx/3xx
- 失敗 = 5xx + タイムアウト + LB/ゲートウェイ起因エラー
- 除外 = 明示的なクライアント起因(4xx)（ただし429は別途扱い）

## 集計方法
- 観測ソース（APM/ログ/メトリクス/RUM）
- 集計窓（月次/週次/日次）
- ラベル（env/service/endpoint）

## エラーバジェット運用
- 算定式
- 閾値（何%消費で凍結/改善を発動するか）
- 例外（緊急パッチ等）
