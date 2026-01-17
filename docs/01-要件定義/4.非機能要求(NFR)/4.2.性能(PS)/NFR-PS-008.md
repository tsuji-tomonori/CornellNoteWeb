# NFR-PS-008 アプリケーション起動時間（スケールアウト時）

## Snow Card
- **Requirement ID**: NFR-PS-008
- **Type**: Non-functional（Performance/Scalability）
- **Description（INCOSE形式）**: 本システムは、スケールアウトまたは再起動時において、アプリケーションインスタンスが60秒以内にリクエスト受け付け可能状態（Ready）にならなければならない。
- **Rationale**: オートスケールや復旧時の性能劣化を抑制するため。
- **Fit Criterion（受入基準/検証）**: 自動スケールアウト時のReady到達時間を計測し、p95が60秒以内であることを確認する（テスト＋分析）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: SRE／開発
- **Supporting Materials**: 起動シーケンス設計、計測結果
- **Conflicts/Dependencies**: 依存サービス初期化（NFR-AV-008）と関連。
