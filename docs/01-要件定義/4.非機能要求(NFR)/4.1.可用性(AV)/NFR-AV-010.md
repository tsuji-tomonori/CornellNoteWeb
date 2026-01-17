# NFR-AV-010 無停止リリース（計画停止の最小化）

## Snow Card
- **Requirement ID**: NFR-AV-010
- **Type**: Non-functional（Availability/Deployability）
- **Description（INCOSE形式）**: 本システムは、計画されたリリースおよび保守作業により、主要機能のユーザ向けダウンタイムを発生させてはならない。
- **Rationale**: サービス停止を伴う運用を常態化させず、業務影響を最小化するため。
- **Fit Criterion（受入基準/検証）**: リリース手順が段階的リリースまたはブルーグリーン等の無停止方式であることを確認し、リリース実績としてユーザ向けダウンタイムが0であることを月次で確認する（検査＋分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: SRE／開発リード
- **Supporting Materials**: リリース手順書、変更管理記録
- **Conflicts/Dependencies**: 段階的リリース（NFR-DEV-003）と依存。DB変更は互換性（NFR-DEV-005）と整合が必要。
