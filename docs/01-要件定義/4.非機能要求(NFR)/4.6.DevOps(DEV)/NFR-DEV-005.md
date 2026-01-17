# NFR-DEV-005 後方互換とバージョニング

## Snow Card
- **Requirement ID**: NFR-DEV-005
- **Type**: Non-functional（Maintainability/Compatibility）
- **Description（INCOSE形式）**: 本システムは、外部公開APIおよびデータスキーマの変更において、合意された互換性方針に従い、互換性を破る変更を事前通知なしにリリースしてはならない。
- **Rationale**: 利用者システムの停止を防ぐため。
- **Fit Criterion（受入基準/検証）**: バージョニング規約が存在し、破壊的変更が変更管理とリリースノートに記録されていることを確認する（検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: プロダクト／開発
- **Supporting Materials**: API規約、変更管理手順
- **Conflicts/Dependencies**: リリース速度との調整が必要。
