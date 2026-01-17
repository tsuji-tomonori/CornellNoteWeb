# NFR-COST-005 ストレージライフサイクル（階層化/削除）

## Snow Card
- **Requirement ID**: NFR-COST-005
- **Type**: Non-functional（Cost Optimization/Compliance）
- **Description（INCOSE形式）**: 本システムは、ログ、バックアップ、および非活性データについて、保持要件を満たす範囲で、ストレージの階層化またはアーカイブを自動適用しなければならない。
- **Rationale**: 長期保管コストを抑制しつつ、保持義務を満たすため。
- **Fit Criterion（受入基準/検証）**: 対象データにライフサイクルポリシーが設定され、保管期間経過後に自動で安価な階層へ移行または削除されることを確認する（検査＋分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: FinOps／運用／監査
- **Supporting Materials**: ストレージ方針、ライフサイクル設定
- **Conflicts/Dependencies**: 監査ログ保持（NFR-PRC-004）・バックアップ保持（NFR-AV-005）と整合が必要。
