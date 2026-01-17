# NFR-OPS-006 メトリクス収集（RED/USE）

## Snow Card
- **Requirement ID**: NFR-OPS-006
- **Type**: Non-functional（Observability）
- **Description（INCOSE形式）**: 本システムは、主要コンポーネントについて、リクエスト数・エラー数・遅延（RED）および利用率・飽和度・エラー（USE）のメトリクスを継続的に収集しなければならない。
- **Rationale**: ボトルネック特定と容量計画を可能にするため。
- **Fit Criterion（受入基準/検証）**: 主要メトリクスが監視基盤に送信され、ダッシュボードに表示され、30日以上の時系列が保持されていることを確認する（検査＋分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: SRE
- **Supporting Materials**: 監視設計、ダッシュボード定義
- **Conflicts/Dependencies**: コスト（メトリクス保管）に影響。
