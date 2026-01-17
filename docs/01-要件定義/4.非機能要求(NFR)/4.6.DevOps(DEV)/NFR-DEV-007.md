# NFR-DEV-007 環境分離（Dev/Staging/Prod）と昇格

## Snow Card
- **Requirement ID**: NFR-DEV-007
- **Type**: Non-functional（Operability/Process）
- **Description（INCOSE形式）**: 本システムは、開発・検証・本番の環境を分離し、成果物が同一パイプラインで段階的に昇格する運用を提供しなければならない。
- **Rationale**: 設定差異による事故を低減し、品質を担保するため。
- **Fit Criterion（受入基準/検証）**: Dev/Staging/Prodが別環境として構成管理され、同一ビルド成果物がStagingを経てProdへ昇格していることを確認する（検査＋分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: 開発リード／SRE
- **Supporting Materials**: 環境構成図、パイプライン定義
- **Conflicts/Dependencies**: データマスキング（NFR-PRC-002）と関連。
