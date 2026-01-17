# NFR-PS-001 API応答時間（サーバ処理）

## Snow Card
- **Requirement ID**: NFR-PS-001
- **Type**: Non-functional（Performance）
- **Description（INCOSE形式）**: 本システムは、通常負荷時において、主要APIの応答時間（サーバ処理時間）をp95 300ms以下に維持しなければならない。
- **Rationale**: 利用者体験と業務生産性を担保するため。
- **Fit Criterion（受入基準/検証）**: APM/トレーシングで主要APIのp95を計測し、連続4週間で基準以内であることを確認する（分析）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: プロダクト／SRE
- **Supporting Materials**: 性能計測設計、APMダッシュボード
- **Conflicts/Dependencies**: 監視（NFR-OPS-002）に依存。
