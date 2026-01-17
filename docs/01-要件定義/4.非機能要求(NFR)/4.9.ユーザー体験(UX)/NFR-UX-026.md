# NFR-UX-026 言語指定（Language of Page/Parts）

## Snow Card
- **Requirement ID**: NFR-UX-026
- **Type**: Non-functional（Cultural/Accessibility｜Volere: 16 Cultural and Political Requirements）
- **Description（INCOSE形式）**: 本システムは、ページの主要言語および部分的に言語が異なる箇所について、言語情報をプログラム的に指定しなければならない。
- **Rationale**: スクリーンリーダーが適切な読み上げ言語に切り替えられるようにし、誤読を防ぐため。
- **Fit Criterion（受入基準/検証）**: 主要画面において、(1)ページ全体の言語がlang属性等で指定されている、(2)外来語や英数字など別言語のまとまりがある場合に部分langが指定される、(3)スクリーンリーダーで読み上げが不自然にならないことを確認する（検査）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 3 / 5
- **Priority**: Should
- **Originator**: UX／ローカライズ
- **Supporting Materials**: i18n方針、言語切替仕様、WCAG 2.2: 3.1.1, 3.1.2（参照）
- **Conflicts/Dependencies**: CMS/翻訳基盤の制約、動的に挿入される文言、ユーザー生成コンテンツに依存。
