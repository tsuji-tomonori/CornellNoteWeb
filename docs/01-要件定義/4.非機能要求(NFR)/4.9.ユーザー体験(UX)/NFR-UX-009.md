# NFR-UX-009 フォーカス可視化とフォーカスの非遮蔽

## Snow Card
- **Requirement ID**: NFR-UX-009
- **Type**: Non-functional（Usability/Accessibility｜Volere: 11 Usability and Humanity Requirements）
- **Description（INCOSE形式）**: 本システムは、キーボードフォーカスを常に視覚的に識別可能とし、固定ヘッダ、バナー、モーダル等の作者生成コンテンツによりフォーカス対象が完全に隠れないようにしなければならない。
- **Rationale**: キーボード利用者が現在位置を見失わずに操作できるようにするため。
- **Fit Criterion（受入基準/検証）**: 主要画面において、(1)すべてのフォーカス可能要素に明確なフォーカスインジケータが表示される、(2)スクロール時や固定要素表示時でもフォーカス対象が完全に隠れない、(3)モーダル表示時はモーダル内にフォーカスが移動し、閉じた後に呼出元へ復帰することを、手動テストで確認する（テスト＋検査）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: UX／フロントエンド
- **Supporting Materials**: フォーカス設計ガイド、WCAG 2.2: 2.4.7, 2.4.11（参照）
- **Conflicts/Dependencies**: 固定ヘッダ/フッタ、トースト/オーバーレイ表示方式、CSSリセット方針に依存。
