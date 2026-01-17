# NFR-UX-019 状態変化の通知（Status Messages）

## Snow Card
- **Requirement ID**: NFR-UX-019
- **Type**: Non-functional（Usability/Accessibility｜Volere: 11 Usability and Humanity Requirements）
- **Description（INCOSE形式）**: 本システムは、保存完了、エラー、読み込み中等の状態変化を提示する場合、フォーカスを不意に移動させることなく支援技術に通知できるようにしなければならない。
- **Rationale**: 視覚に依存せずに、処理結果や進行状況を把握できるようにするため。
- **Fit Criterion（受入基準/検証）**: 主要フローにおいて、(1)トースト/アラート/バリデーション結果等がスクリーンリーダーで自動的に読み上げられる（aria-live等）、(2)通知によりフォーカスが強制移動しない、(3)通知の内容が具体的（何が起きたか/次に何をすべきか）であることを確認する（テスト）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: フロントエンド／UX
- **Supporting Materials**: 通知コンポーネント仕様、WCAG 2.2: 4.1.3（参照）
- **Conflicts/Dependencies**: 既存通知ライブラリ、リアルタイム更新頻度、ノイズの多い通知設計に依存。
