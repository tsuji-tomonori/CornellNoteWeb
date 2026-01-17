# NFR-UX-007 リフロー・拡大・画面向きへの対応

## Snow Card
- **Requirement ID**: NFR-UX-007
- **Type**: Non-functional（Look&Feel/Accessibility｜Volere: 10 Look and Feel Requirements）
- **Description（INCOSE形式）**: 本システムは、表示倍率200%および幅320CSS px相当の画面条件においても、主要機能の利用に支障となる水平スクロールや情報欠落を発生させずに閲覧・操作できなければならない。
- **Rationale**: 低視力の利用者やモバイル利用者が拡大・縮小・向き変更を行っても、同等の情報取得と操作ができるようにするため。
- **Fit Criterion（受入基準/検証）**: 主要画面に対して、(1)ブラウザ拡大200%で情報/操作の欠落がない、(2)幅320CSS px相当で二次元スクロールが発生しない（例外：データ表など必要最小限を除く）、(3)縦横いずれの画面向きでも利用可能（必須要件がある場合は根拠を提示）であることを、実機/エミュレータで確認する（テスト＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: UX／フロントエンド
- **Supporting Materials**: レスポンシブ設計指針、WCAG 2.2: 1.4.4, 1.4.10, 1.3.4（参照）
- **Conflicts/Dependencies**: 複雑なデータテーブル、外部埋め込みコンテンツ、固定レイアウト前提の画面設計に依存。
