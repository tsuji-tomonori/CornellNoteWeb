# NFR-PRC-004 監査証跡（改ざん困難・長期保管）

## Snow Card
- **Requirement ID**: NFR-PRC-004
- **Type**: Non-functional（Auditability）
- **Description（INCOSE形式）**: 本システムは、重要操作の監査証跡を改ざん困難な形で記録し、1年間以上保持しなければならない。
- **Rationale**: 監査・不正調査・説明責任の担保のため。
- **Fit Criterion（受入基準/検証）**: 監査対象イベント一覧が定義され、ログ保管が1年以上であること、削除/改ざんが制限されていることを設定とサンプルログで確認する（検査＋分析）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: 監査／セキュリティ
- **Supporting Materials**: 監査要件、ログ保管ポリシー
- **Conflicts/Dependencies**: ログ保管量はコスト（NFR-COST-005）に影響。
