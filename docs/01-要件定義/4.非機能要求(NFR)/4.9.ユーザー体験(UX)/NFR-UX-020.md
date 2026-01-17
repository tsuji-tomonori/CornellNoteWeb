# NFR-UX-020 閃光・発作リスクの回避

## Snow Card
- **Requirement ID**: NFR-UX-020
- **Type**: Non-functional（Look&Feel/Accessibility｜Volere: 10 Look and Feel Requirements）
- **Description（INCOSE形式）**: 本システムは、1秒間に3回を超えて閃光するコンテンツ、または発作を誘発し得る点滅・パターンを表示してはならない。
- **Rationale**: 光過敏性発作のリスクを低減し、安全に利用できるようにするため。
- **Fit Criterion（受入基準/検証）**: UIアニメーション、ローディング表示、プロモーション表示等について、(1)点滅頻度が閾値を超えない、(2)意図せず自動再生される点滅表現がないことを、デザインレビュー＋実機確認で満たす（検査）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: UX／フロントエンド
- **Supporting Materials**: アニメーション指針、WCAG 2.2: 2.3.1（参照）
- **Conflicts/Dependencies**: マーケティング要請、外部広告/埋込コンテンツ、既存ローディング表現に依存。
