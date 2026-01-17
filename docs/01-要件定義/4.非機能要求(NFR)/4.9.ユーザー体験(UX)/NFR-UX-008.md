# NFR-UX-008 キーボード操作性とフォーカス順序

## Snow Card
- **Requirement ID**: NFR-UX-008
- **Type**: Non-functional（Usability/Accessibility｜Volere: 11 Usability and Humanity Requirements）
- **Description（INCOSE形式）**: 本システムは、すべての主要機能をキーボードのみで操作可能とし、キーボードトラップを発生させず、フォーカス順序を意味のある順序に維持し、繰り返しブロックをスキップできる手段を提供しなければならない。
- **Rationale**: マウス操作が困難な利用者が、フォーム入力・遷移・操作を完結できるようにするため。
- **Fit Criterion（受入基準/検証）**: 主要ユーザーフローを対象に、(1)Tab/Shift+Tab/Enter/Space/矢印キー等で全機能が完了できる、(2)フォーカスが閉じ込められない、(3)フォーカス順序が視覚的・論理的順序と整合し、(4)「本文へスキップ」等でヘッダ/ナビ等の繰り返し領域を回避できることを、キーボードのみの手動テストで確認する（テスト）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: UX／フロントエンド／QA
- **Supporting Materials**: 操作仕様（キーボード操作一覧）、WCAG 2.2: 2.1.1, 2.1.2, 2.4.1, 2.4.3（参照）
- **Conflicts/Dependencies**: カスタムUI（独自セレクト/グリッド/エディタ）、サードパーティUIライブラリ、ショートカット衝突に依存。
