# NFR-OPS-003 アラート通知とエスカレーション

## Snow Card
- **Requirement ID**: NFR-OPS-003
- **Type**: Non-functional（Operability）
- **Description（INCOSE形式）**: 本システムは、SLO違反の予兆または重大障害を検知した場合に、所定の連絡手段でアラートを通知し、エスカレーション手順に従って対応者へ連携しなければならない。
- **Rationale**: 検知遅れによる影響拡大を防ぐため。
- **Fit Criterion（受入基準/検証）**: 重大アラートの通知経路が冗長化され、月次の通知テストで到達確認ができること（テスト）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: 運用
- **Supporting Materials**: オンコール体制、連絡網
- **Conflicts/Dependencies**: 運用時間（24x7か否か）に依存。
