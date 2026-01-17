# NFR-AV-003 自動フェイルオーバ

## Snow Card
- **Requirement ID**: NFR-AV-003
- **Type**: Non-functional（Resilience）
- **Description（INCOSE形式）**: 本システムは、本番環境において、主要コンポーネント障害時に自動的にフェイルオーバし、サービス提供を継続しなければならない。
- **Rationale**: 復旧を人手に依存させず、停止時間を短縮するため。
- **Fit Criterion（受入基準/検証）**: 障害注入または計画停止によりフェイルオーバが動作し、主要ユースケースが継続利用可能であることを確認する。フェイルオーバ検知から復旧までの所要時間を測定し記録する（テスト＋分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: SRE
- **Supporting Materials**: フェイルオーバ設計、テスト結果
- **Conflicts/Dependencies**: NFR-AV-002に依存。監視（NFR-OPS-005）と連携。
