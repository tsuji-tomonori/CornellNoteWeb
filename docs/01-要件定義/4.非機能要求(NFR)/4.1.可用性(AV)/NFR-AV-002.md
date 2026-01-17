# NFR-AV-002 冗長化（単一障害点の排除）

## Snow Card
- **Requirement ID**: NFR-AV-002
- **Type**: Non-functional（Resilience）
- **Description（INCOSE形式）**: 本システムは、本番環境において、単一障害点を排除するために、計算資源およびデータストアを少なくとも2つ以上の障害分離単位に冗長配置しなければならない。
- **Rationale**: 単一の障害で全停止するリスクを低減するため。
- **Fit Criterion（受入基準/検証）**: 構成図およびIaCから、主要コンポーネント（計算、負荷分散、データストア、メッセージング）が2つ以上の障害分離単位（例：AZ/ゾーン等）に配置されていることを確認する（検査）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: SRE／プラットフォーム
- **Supporting Materials**: 参照アーキテクチャ、IaCリポジトリ
- **Conflicts/Dependencies**: コスト要件（NFR-COST-*）とトレードオフ。
