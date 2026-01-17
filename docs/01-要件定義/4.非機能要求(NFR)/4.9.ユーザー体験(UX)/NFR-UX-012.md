# NFR-UX-012 複雑ジェスチャ・誤操作防止・端末動作への依存回避

## Snow Card
- **Requirement ID**: NFR-UX-012
- **Type**: Non-functional（Usability/Accessibility｜Volere: 11 Usability and Humanity Requirements）
- **Description（INCOSE形式）**: 本システムは、複雑なジェスチャ（多点・パスベース）または端末の動作（シェイク等）を必須とせず、単一ポインターによる代替手段および誤操作を取り消せる仕組みを提供しなければならない。
- **Rationale**: タッチ操作が困難な利用者や補助入力デバイス利用者が、確実に操作できるようにするため。
- **Fit Criterion（受入基準/検証）**: 主要画面において、(1)ピンチ/スワイプ等の複雑ジェスチャに依存する機能はボタン等の単一操作で代替できる、(2)クリック/タップで即時確定する破壊的操作は確認または取り消し手段を提供する、(3)端末動作で起動する機能はUI上からも起動/無効化できることを、モバイル端末で確認する（テスト）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: UX／フロントエンド
- **Supporting Materials**: モバイル操作仕様、WCAG 2.2: 2.5.1, 2.5.2, 2.5.4（参照）
- **Conflicts/Dependencies**: 地図/画像編集などジェスチャ中心UI、ネイティブSDKの制約に依存。
