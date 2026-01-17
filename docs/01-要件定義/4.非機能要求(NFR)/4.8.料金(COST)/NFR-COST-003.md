# NFR-COST-003 非本番の稼働抑制（スケジュール停止）

## Snow Card
- **Requirement ID**: NFR-COST-003
- **Type**: Non-functional（Cost Optimization）
- **Description（INCOSE形式）**: 本システムは、非本番環境のリソースを定義されたスケジュールに従って停止または縮退し、不要な課金を抑制しなければならない。
- **Rationale**: 継続的な無駄コスト削減のため。
- **Fit Criterion（受入基準/検証）**: スケジュールが設定され、月次で非本番コストがベースライン比で削減されていること、例外が記録されることを確認する（分析＋検査）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 3 / 5
- **Priority**: Should
- **Originator**: FinOps／開発
- **Supporting Materials**: 環境運用ルール
- **Conflicts/Dependencies**: 開発の利便性との調整が必要。
