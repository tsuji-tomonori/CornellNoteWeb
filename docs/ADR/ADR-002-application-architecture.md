# ADR-002: アプリケーション構成（MVC + API + Worker）

- Status: Accepted
- Date: 2026-01-17

## Context
Webフロントエンド、APIサービス、非同期処理ワーカーに責務を分割し、
ノート作成/検索/共有/PDF出力などの主要ユースケースを安定的に提供する必要がある。
特にPDF生成やメール送信などは同期処理に含めると性能要件を満たしづらい。

## Decision
アプリケーションは以下の構成を採用する。
- Web Frontend: ThymeleafによるUIレンダリング
- API Service: 認証/CRUD/検索/共有/エクスポートを担当
- Job Worker: PDF生成やメール送信などの非同期処理を担当

## Consequences
- フロントとAPIを同一アプリケーション内で構成しつつ、責務を分割できる。
- 非同期処理により、API応答性能とスループット要件を満たしやすい。
- ワーカーの運用やキュー処理の監視が追加で必要になる。

## Alternatives
- 同期処理のみでPDF生成まで完結
- SPA + API構成に一本化
- バッチ専用サービスの追加

## References
- docs/02-基本設計/1.アーキテクチャ/ARCH-Logical.md
- docs/02-基本設計/1.アーキテクチャ/ARCH-Deployment.md
- docs/02-基本設計/4.API設計/API-Overview.md
- docs/02-基本設計/5.非機能設計/NFR-PerformanceDesign.md
