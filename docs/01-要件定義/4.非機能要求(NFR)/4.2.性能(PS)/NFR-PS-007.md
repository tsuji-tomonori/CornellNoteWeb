# NFR-PS-007 データベース性能（主要クエリ）

## Snow Card
- **Requirement ID**: NFR-PS-007
- **Type**: Non-functional（Performance/Data）
- **Description（INCOSE形式）**: 本システムは、通常負荷時において、主要データベースクエリの応答時間をp95 100ms以下に維持しなければならない。
- **Rationale**: アプリ全体の遅延要因の多くがデータアクセスに起因するため。
- **Fit Criterion（受入基準/検証）**: DBモニタリングまたはAPMで主要クエリのp95を計測し、連続4週間で基準以内であることを確認する（分析）。主要クエリの一覧は性能設計書に明記する（検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: DBA／SRE
- **Supporting Materials**: 性能設計書、クエリ一覧
- **Conflicts/Dependencies**: データモデル変更（NFR-DATA-003）と密結合。
