# NFR-PS-002 画面応答（ユーザ体感）

## Snow Card
- **Requirement ID**: NFR-PS-002
- **Type**: Non-functional（Usability/Performance）
- **Description（INCOSE形式）**: 本システムは、主要画面のユーザインタラクション（検索・登録等）に対して、ユーザが結果を視認可能になるまでの時間をp95 2.0秒以下に維持しなければならない。
- **Rationale**: UXを確保し離脱を防止するため。
- **Fit Criterion（受入基準/検証）**: RUMまたは合成監視で主要操作のp95を測定し、SLOを満たすことを確認する（テスト＋分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: 事業部門／UX
- **Supporting Materials**: RUM設計、UX指標定義
- **Conflicts/Dependencies**: ネットワーク条件・端末条件の前提を定義する必要がある。
