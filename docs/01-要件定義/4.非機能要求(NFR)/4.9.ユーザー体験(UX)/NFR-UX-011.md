# NFR-UX-011 ドラッグ操作の代替手段

## Snow Card
- **Requirement ID**: NFR-UX-011
- **Type**: Non-functional（Usability/Accessibility｜Volere: 11 Usability and Humanity Requirements）
- **Description（INCOSE形式）**: 本システムは、ドラッグ&ドロップ等のドラッグ操作を伴う機能を提供する場合、ドラッグを伴わない単一ポインター操作による代替手段を提供しなければならない。
- **Rationale**: ドラッグ操作が困難な利用者でも、並び替えや移動などの機能を利用できるようにするため。
- **Fit Criterion（受入基準/検証）**: ドラッグを必要とする機能ごとに、(1)「上へ/下へ移動」「追加/削除」「並び替え」等の代替UIを提供し、(2)キーボード操作でも同等の結果を達成できることを、主要フローで確認する（テスト）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: UX／フロントエンド
- **Supporting Materials**: 操作仕様、WCAG 2.2: 2.5.7（参照）
- **Conflicts/Dependencies**: カンバン/ホワイトボード等の高度なインタラクション、サードパーティのD&Dライブラリに依存。
