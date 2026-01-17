# 非機能設計（NFR）索引

## 目的
要件定義で合意した非機能要求（NFR）を、基本設計レベルの設計方針・構成・運用/検証方法へ落とし込む。
本フォルダは「NFR → 設計 → 検証/運用」のトレーサビリティ起点とする。

## 対象NFRカテゴリ
- 可用性（NFR-AV-001〜010）
- 性能（NFR-PS-001〜010）
- セキュリティ（NFR-SEC-001〜012）
- プライバシー/コンプライアンス（NFR-PRC-001〜008）
- 可観測性（NFR-OPS-001〜009）
- DevOps（NFR-DEV-001〜009）
- 相互運用性（NFR-INT-001〜005）
- 料金/コスト（NFR-COST-001〜005）
- UX/アクセシビリティ（NFR-UX-001〜026）
- データ（NFR-DATA-001〜005）

## ドキュメント一覧（本フォルダ）
- NFR-AvailabilityDesign.md
- NFR-PerformanceDesign.md
- NFR-SecurityDesign.md
- NFR-PrivacyDesign.md
- NFR-ObservabilityDesign.md
- NFR-DevOpsDesign.md
- NFR-InteroperabilityDesign.md
- NFR-CostDesign.md
- NFR-UXAccessibilityDesign.md
- NFR-DataReliabilityDesign.md

## 関連ドキュメント（他フォルダ）
- デプロイ構成: docs/02-基本設計/1.アーキテクチャ/ARCH-Deployment.md
- 認証: docs/02-基本設計/4.API設計/API-Auth.md
- エラー体系（traceId等）: docs/02-基本設計/4.API設計/API-ErrorModel.md
- データ保持/削除: docs/02-基本設計/2.データ設計/DATA-Retention.md
- UI基本: docs/02-基本設計/3.UI設計/*

## トレーサビリティ方針
- 各NFR設計書には「対応NFR」「設計方針」「運用/検証（Fit Criterionの実現方法）」を必ず記載する。
- 詳細設計（docs/03-詳細設計）には、本書で定義した方針を具体的な設定値・手順・テストケースへ展開する。
