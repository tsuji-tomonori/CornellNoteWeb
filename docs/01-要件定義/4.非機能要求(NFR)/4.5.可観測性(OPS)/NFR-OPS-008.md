# NFR-OPS-008 エラーバジェット運用（SLO駆動）

## Snow Card
- **Requirement ID**: NFR-OPS-008
- **Type**: Non-functional（SRE/Process）
- **Description（INCOSE形式）**: 本システムは、SLOに基づくエラーバジェットを定義し、エラーバジェットを超過した場合は変更凍結または品質改善を優先しなければならない。
- **Rationale**: 速度と信頼性のバランスを定量的に管理するため。
- **Fit Criterion（受入基準/検証）**: エラーバジェット算定方法が定義され、月次で消費状況がレポートされ、超過時の運用ルール（変更凍結等）が適用された記録があること（検査＋分析）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Could
- **Originator**: SRE／プロダクト
- **Supporting Materials**: SLO運用ルール
- **Conflicts/Dependencies**: 開発計画（NFR-DEV-002）との調整が必要。
