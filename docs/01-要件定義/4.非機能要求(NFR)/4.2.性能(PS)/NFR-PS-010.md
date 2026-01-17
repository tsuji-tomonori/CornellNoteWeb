# NFR-PS-010 APIペイロード制限

## Snow Card
- **Requirement ID**: NFR-PS-010
- **Type**: Non-functional（Performance/Network）
- **Description（INCOSE形式）**: 本システムは、主要APIにおいて、単一リクエストおよび単一レスポンスのペイロードサイズを1MB以下に制限しなければならない。
- **Rationale**: ネットワーク遅延・メモリ消費・タイムアウトのリスクを抑制するため。
- **Fit Criterion（受入基準/検証）**: APIゲートウェイまたはアプリ層でペイロード制限が設定され、超過時に標準エラーを返すことを確認する（テスト＋検査）。例外（大容量転送）はストリーミングまたは署名付きURL等の方式で設計し、例外として記録する（検査）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 3 / 5
- **Priority**: Could
- **Originator**: アーキテクト／開発
- **Supporting Materials**: API設計標準
- **Conflicts/Dependencies**: 既存クライアント互換（NFR-DEV-005）と調整が必要。
