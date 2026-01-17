# NFR-DEV-002 CI/CDと品質ゲート

## Snow Card
- **Requirement ID**: NFR-DEV-002
- **Type**: Non-functional（Quality/Process）
- **Description（INCOSE形式）**: 本システムは、変更を本番へ反映する際に、CI/CDパイプラインを通過し、静的解析・依存関係スキャン・自動テストの品質ゲートを満たさなければならない。
- **Rationale**: 品質・セキュリティを継続的に担保するため。
- **Fit Criterion（受入基準/検証）**: 全リリースがパイプライン経由であること、ゲート条件が文書化され、違反時にデプロイが停止することを確認する（検査＋テスト）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: 開発リード／セキュリティ
- **Supporting Materials**: パイプライン定義、Secure SDLC
- **Conflicts/Dependencies**: リリース速度とのトレードオフ。
