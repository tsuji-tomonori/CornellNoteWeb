# NFR-AV-008 タイムアウト・リトライ・遮断（Circuit Breaker）標準

## Snow Card
- **Requirement ID**: NFR-AV-008
- **Type**: Non-functional（Reliability）
- **Description（INCOSE形式）**: 本システムは、外部依存サービス（DB、外部API、メッセージング等）への呼び出しに対して、タイムアウト、最大リトライ回数、および指数バックオフを設定し、連続失敗時に遮断（サーキットブレーカ）できるようにしなければならない。
- **Rationale**: 依存先障害の波及（スレッド枯渇、キュー飽和、連鎖障害）を防止するため。
- **Fit Criterion（受入基準/検証）**: すべての依存呼び出しにタイムアウトが設定されていること、最大リトライ回数が3回以下であること、遮断設定（失敗率閾値、半開放復帰条件）が構成として管理されていることをコード/設定レビューで確認する（検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: アーキテクト／SRE
- **Supporting Materials**: 実装標準、例外設計
- **Conflicts/Dependencies**: 性能要件（NFR-PS-*）と調整が必要。
