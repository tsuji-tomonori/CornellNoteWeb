# ADR-006: 非機能運用方針（SLO/セキュリティ/CIゲート）

- Status: Accepted
- Date: 2026-01-17

## Context
可用性、性能、セキュリティ、運用監視、CI/CD品質ゲートを含む非機能要求が定義されている。
実装と運用の段階で設計意図を継続的に維持できる運用方針が必要である。

## Decision
- SLO/SLIを定義し、監視とアラート基準を運用に組み込む。
- セキュリティ統制（IAM/暗号/脆弱性管理/WAF）を標準化する。
- CI/CDで静的解析、依存スキャン、主要ユースケースの自動テストを必須ゲート化する。

## Consequences
- 品質とセキュリティの維持が継続的に担保される。
- 運用コストと監視設定の初期投資が必要になる。

## Alternatives
- リリース前手動レビューのみで品質を担保
- 監視/アラートを最低限に抑える

## References
- docs/02-基本設計/5.非機能設計/NFR-PerformanceDesign.md
- docs/02-基本設計/5.非機能設計/NFR-SecurityDesign.md
- docs/02-基本設計/5.非機能設計/NFR-DevOpsDesign.md
- docs/03-詳細設計/3.非機能詳細/DD-Observability-MetricsAlerts.md
- docs/03-詳細設計/3.非機能詳細/DD-Security-Controls.md
- docs/03-詳細設計/3.非機能詳細/DD-CICD-QualityGates.md
