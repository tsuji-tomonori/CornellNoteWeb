# NFR-UX-004 非テキストコンテンツの代替とアクセシブルネーム

## Snow Card
- **Requirement ID**: NFR-UX-004
- **Type**: Non-functional（Usability/Accessibility｜Volere: 11 Usability and Humanity Requirements）
- **Description（INCOSE形式）**: 本システムは、非テキストコンテンツ（画像、アイコン、入力コントロール等）を提示する場合、その目的・意味を伝えるテキスト代替およびアクセシブルネームをプログラム的に提供しなければならない。
- **Rationale**: スクリーンリーダー利用者や音声入力利用者が、要素の意味と操作対象を正しく理解・操作できるようにするため。
- **Fit Criterion（受入基準/検証）**: 主要画面において、(1)情報を伝える画像は代替テキスト（alt等）が意味を説明し、装飾画像は空のaltとする、(2)すべての操作可能要素に一意のアクセシブルネーム（label/aria-label等）が付与され、(3)表示ラベルとアクセシブルネームが不整合にならないことを、スクリーンリーダー読み上げ確認＋自動検査で確認する（テスト＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: UX／フロントエンド
- **Supporting Materials**: デザインシステム（ラベル命名規約）、WCAG 2.2: 1.1.1, 2.5.3, 4.1.2（参照）
- **Conflicts/Dependencies**: アイコンのみボタン、動的生成UI、翻訳/ローカライズ、外部ウィジェットのラベリング仕様に依存。
