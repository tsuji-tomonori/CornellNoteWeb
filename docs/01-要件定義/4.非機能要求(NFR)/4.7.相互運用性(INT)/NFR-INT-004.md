# NFR-INT-004 テレメトリ標準（OpenTelemetry）

## Snow Card
- **Requirement ID**: NFR-INT-004
- **Type**: Non-functional（Observability/Portability）
- **Description（INCOSE形式）**: 本システムは、ログ、メトリクス、およびトレースの出力において、OpenTelemetry等の標準フォーマット/SDKを用い、観測データの移植性を確保しなければならない。
- **Rationale**: ツール変更やマルチ環境での運用を容易にするため。
- **Fit Criterion（受入基準/検証）**: 主要サービスが標準SDKでテレメトリを出力し、バックエンドを変更しても収集できることを検証する（テスト＋検査）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 3 / 5
- **Priority**: Could
- **Originator**: SRE／開発
- **Supporting Materials**: テレメトリ設計
- **Conflicts/Dependencies**: 既存監視基盤との互換に注意。
