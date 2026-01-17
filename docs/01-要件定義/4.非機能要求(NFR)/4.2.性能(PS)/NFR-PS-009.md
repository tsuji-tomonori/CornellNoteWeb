# NFR-PS-009 バッチ処理完了時間（運用ウィンドウ）

## Snow Card
- **Requirement ID**: NFR-PS-009
- **Type**: Non-functional（Performance/Operability）
- **Description（INCOSE形式）**: 本システムは、日次バッチ処理を、運用で合意した実行ウィンドウ内（例：02:00〜04:00）に完了しなければならない。
- **Rationale**: 翌営業日の業務開始までにデータを整合させるため。
- **Fit Criterion（受入基準/検証）**: 代表データ量でのバッチ試験により、ウィンドウ内に完了することを確認し、実行時間の推移をメトリクスとして記録する（テスト＋分析）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: 運用／開発
- **Supporting Materials**: バッチ設計、運用カレンダー
- **Conflicts/Dependencies**: データ量増加時の再見積（キャパシティ）が必要。
