# NFR-UX-001 エラーメッセージ品質（利用者向け）

## Snow Card
- **Requirement ID**: NFR-UX-001
- **Type**: Non-functional（Usability）
- **Description（INCOSE形式）**: 本システムは、利用者に提示するエラーについて、原因カテゴリ、利用者が取るべき対応、問い合わせ用識別子を含むメッセージを表示しなければならない。
- **Rationale**: 利用者の自己解決率を高め、問い合わせコストを低減するため。
- **Fit Criterion（受入基準/検証）**: 主要エラーシナリオで、メッセージに対応策と問い合わせID（相関ID等）が表示されることを確認する（テスト＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 3 / 5
- **Priority**: Should
- **Originator**: UX／運用
- **Supporting Materials**: UXライティングガイド、エラー一覧
- **Conflicts/Dependencies**: ログ相関（NFR-OPS-001）と整合が必要。
