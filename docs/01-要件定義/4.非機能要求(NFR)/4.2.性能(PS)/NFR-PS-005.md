# NFR-PS-005 レート制御（スロットリング）

## Snow Card
- **Requirement ID**: NFR-PS-005
- **Type**: Non-functional（Reliability/Security）
- **Description（INCOSE形式）**: 本システムは、過負荷または不正利用を防止するために、クライアント単位のリクエストを制限し、制限超過時は標準化されたエラーレスポンスを返却しなければならない。
- **Rationale**: 障害連鎖やDoS的振る舞いを抑止するため。
- **Fit Criterion（受入基準/検証）**: レート制限値が設定され、超過時にHTTP 429等の仕様どおりの応答となることをテストで確認する（テスト）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: SRE／セキュリティ
- **Supporting Materials**: API仕様、運用ポリシー
- **Conflicts/Dependencies**: 顧客要件（許容スループット）との調整が必要。
