# NFR-UX-015 同一プロセス内の再入力（Redundant Entry）の最小化

## Snow Card
- **Requirement ID**: NFR-UX-015
- **Type**: Non-functional（Usability/Accessibility｜Volere: 11 Usability and Humanity Requirements）
- **Description（INCOSE形式）**: 本システムは、同一プロセス内で既に入力済みまたは提供済みの情報を再入力させる場合、当該情報を自動入力または選択可能として提供し、不要な再入力を要求してはならない。
- **Rationale**: 認知・記憶負荷を低減し、入力困難な利用者でも手続きを完了できるようにするため。
- **Fit Criterion（受入基準/検証）**: 複数ステップの登録/申請フローについて、(1)前ステップで入力した氏名/住所等が次ステップで自動表示される、または選択して再利用できる、(2)例外（セキュリティ上の再入力、情報の失効、必須要件）を適用する場合は理由を記録することを、仕様レビュー＋動作確認で満たす（検査）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: UX／プロダクト
- **Supporting Materials**: ユーザーフロー図、入力項目一覧、WCAG 2.2: 3.3.7（参照）
- **Conflicts/Dependencies**: セキュリティ要件（再認証/再入力）、既存業務プロセス、データ保持方針に依存。
