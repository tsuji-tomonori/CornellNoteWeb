# NFR-AV-005 バックアップ取得と保持

## Snow Card
- **Requirement ID**: NFR-AV-005
- **Type**: Non-functional（Backup）
- **Description（INCOSE形式）**: 本システムは、永続データに対して、少なくとも日次のバックアップを取得し、90日以上保持しなければならない。
- **Rationale**: 誤操作・障害・ランサムウェア等からの復旧を可能にするため。
- **Fit Criterion（受入基準/検証）**: バックアップジョブの成功率が月次で99%以上であること、保持期間が設定により担保されていること、復旧手順が文書化されていることを確認する（分析＋検査）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: 運用／監査
- **Supporting Materials**: バックアップ設計、保管ポリシー
- **Conflicts/Dependencies**: RPO要件（NFR-AV-006）により頻度の見直しが必要な場合がある。
