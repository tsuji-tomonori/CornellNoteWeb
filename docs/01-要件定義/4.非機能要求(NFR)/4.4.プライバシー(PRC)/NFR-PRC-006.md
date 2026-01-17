# NFR-PRC-006 同意と利用目的の管理

## Snow Card
- **Requirement ID**: NFR-PRC-006
- **Type**: Non-functional（Privacy/Compliance）
- **Description（INCOSE形式）**: 本システムは、個人情報を取り扱う場合、収集・利用目的および同意状態を記録し、同意の範囲を超える利用を防止しなければならない。
- **Rationale**: プライバシー規制および契約上の義務を満たすため。
- **Fit Criterion（受入基準/検証）**: 同意状態がデータモデルに保持され、同意がない場合に対象処理が拒否または限定されることをテストで確認する（テスト＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Should
- **Originator**: 法務／プロダクト
- **Supporting Materials**: プライバシーポリシー、同意フロー設計
- **Conflicts/Dependencies**: UX（NFR-UX-002）と整合が必要。
