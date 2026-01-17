# ADR-005: PDF生成方式（非同期ワーカー）

- Status: Accepted
- Date: 2026-01-17

## Context
PDFエクスポートは外部ライブラリや生成処理に時間がかかる。
同期処理に含めるとAPI応答性能やユーザ体感性能の要件を満たしづらい。

## Decision
PDF生成はジョブキュー + ワーカーによる非同期処理とする。
APIはジョブ登録と結果取得を分離し、完了後にObject Storageから取得可能にする。

## Consequences
- API応答性能を維持しやすい。
- ジョブ管理、再実行、失敗通知の運用が必要になる。

## Alternatives
- API同期処理で即時生成
- クライアント側でPDF生成

## References
- docs/02-基本設計/0.全体概要/SYS-Overview.md
- docs/02-基本設計/1.アーキテクチャ/ARCH-Logical.md
- docs/02-基本設計/4.API設計/API-Overview.md
- docs/02-基本設計/5.非機能設計/NFR-PerformanceDesign.md
