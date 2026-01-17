# NFR-UX-022 ダウンロード資料・通知（メール等）のアクセシビリティ

## Snow Card
- **Requirement ID**: NFR-UX-022
- **Type**: Non-functional（Operational/Accessibility｜Volere: 13 Operational and Environmental Requirements）
- **Description（INCOSE形式）**: 本システムは、利用者に提供するダウンロード資料（PDF等）および通知コンテンツ（メール等）について、アクセシブルな形式を優先し、提供する場合は読み上げ順序・見出し・代替テキスト等が適切になるよう作成しなければならない。
- **Rationale**: Web UI以外の成果物でも情報取得が妨げられないようにし、問い合わせ・逸失を低減するため。
- **Fit Criterion（受入基準/検証）**: 提供する資料/通知のうち代表的なテンプレートを対象に、(1)メールは構造化HTML（見出し/リスト/リンクテキスト等）で作成され、画像のみで情報を伝えない、(2)PDF等を提供する場合はタグ付け、読み順、代替テキストが適切であることを検査ツール/レビューで確認する（検査）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 3 / 5
- **Priority**: Should
- **Originator**: プロダクト／コンテンツ／CS
- **Supporting Materials**: 通知テンプレート一覧、文書作成ガイド（PDF/HTML）、WCAG2ICT等の関連ガイダンス（参照）
- **Conflicts/Dependencies**: 外部メール配信サービスのテンプレート制約、既存PDF資産、ブランド表現（画像中心）に依存。
