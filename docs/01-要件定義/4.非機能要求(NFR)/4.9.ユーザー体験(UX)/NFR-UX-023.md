# NFR-UX-023 クラウドCI/CDにおけるアクセシビリティ検査の自動化とゲート

## Snow Card
- **Requirement ID**: NFR-UX-023
- **Type**: Non-functional（Quality/Accessibility｜Volere: 14 Maintainability and Support Requirements）
- **Description（INCOSE形式）**: 本システムは、クラウド上のCI/CDパイプラインでアクセシビリティ自動検査を継続的に実施し、重大な不適合を検出したビルドは本番環境へデプロイできないようにしなければならない。
- **Rationale**: リグレッションを早期に検出し、修正コストとリリースリスクを低減するため。
- **Fit Criterion（受入基準/検証）**: CI/CDにおいて、(1)PR/マージ時に自動検査（例：axe/pa11y/Lighthouse相当）を実行し、重大/深刻の違反が閾値（例：0件）を超えた場合は失敗扱いとする、(2)結果レポートをアーティファクトとして保存し追跡可能にすることを、パイプライン設定と実行結果で確認する（検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: QA／DevOps
- **Supporting Materials**: CI/CD設計、テスト戦略、検査ツール設定、改善チケット運用ルール
- **Conflicts/Dependencies**: 誤検知/過検知への対応、E2E環境の安定性、セキュリティ制約（テスト用認証）に依存。
