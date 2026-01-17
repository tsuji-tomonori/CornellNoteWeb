# NFR-UX-014 フォームのラベル・エラー提示・入力支援

## Snow Card
- **Requirement ID**: NFR-UX-014
- **Type**: Non-functional（Usability/Accessibility｜Volere: 11 Usability and Humanity Requirements）
- **Description（INCOSE形式）**: 本システムは、入力フォームを提供する場合、各入力項目にラベルと説明を関連付け、入力エラーを特定してテキストで提示し、修正方法の提案および重要データ送信前の確認手段を提供しなければならない。
- **Rationale**: 認知負荷を下げ、支援技術利用者を含む全利用者が入力を完了できるようにするため。
- **Fit Criterion（受入基準/検証）**: 主要フォームにおいて、(1)label/for等でラベルが関連付く、(2)必須/形式等の制約が事前に説明される、(3)エラー時に該当項目とエラー理由がテキストで示され、スクリーンリーダーでも認識できる、(4)金銭・契約・削除など重大操作は送信前に確認/取消手段があることを、手動テスト＋スクリーンリーダー確認で満たす（テスト）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: UX／フロントエンド／バックエンド
- **Supporting Materials**: フォーム設計ガイド、WCAG 2.2: 1.3.1, 3.3.1, 3.3.2, 3.3.3, 3.3.4（参照）
- **Conflicts/Dependencies**: バックエンドのバリデーション仕様、リアルタイム検証のUX、複雑な入力ウィジェットに依存。
