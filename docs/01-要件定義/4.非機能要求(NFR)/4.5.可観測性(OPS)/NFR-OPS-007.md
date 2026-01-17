# NFR-OPS-007 分散トレーシング（トレース連携）

## Snow Card
- **Requirement ID**: NFR-OPS-007
- **Type**: Non-functional（Observability）
- **Description（INCOSE形式）**: 本システムは、分散トレーシングを実装し、ユーザ要求に対する処理経路をサービス間で追跡できるようにしなければならない。
- **Rationale**: マイクロサービス/外部依存が増えるほど原因特定が困難になるため。
- **Fit Criterion（受入基準/検証）**: トレースコンテキストがサービス間で伝播し、主要ユースケースで単一トレースとして可視化できることを確認する（テスト＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: SRE／開発
- **Supporting Materials**: トレーシング設計
- **Conflicts/Dependencies**: ログ相関（NFR-OPS-001）と整合が必要。
