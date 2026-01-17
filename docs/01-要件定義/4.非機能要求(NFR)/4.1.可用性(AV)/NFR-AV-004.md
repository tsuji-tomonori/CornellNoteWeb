# NFR-AV-004 縮退運転（Graceful Degradation）

## Snow Card
- **Requirement ID**: NFR-AV-004
- **Type**: Non-functional（Reliability/Usability）
- **Description（INCOSE形式）**: 本システムは、一部機能または依存サービスが利用不能な場合に、重要度の高い機能を優先し、非重要機能を制限することで縮退運転を提供しなければならない。
- **Rationale**: 全面停止を回避し、最小限の事業継続を確保するため。
- **Fit Criterion（受入基準/検証）**: 縮退対象機能と優先順位が文書化され、縮退時にユーザへ制限状態が明示されること。縮退シナリオのテストで、重要機能が利用可能であることを確認する（検査＋テスト）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: 事業部門／運用
- **Supporting Materials**: 縮退設計、UXガイド
- **Conflicts/Dependencies**: 機能要件・UX要件（NFR-UX-*）と整合が必要。
