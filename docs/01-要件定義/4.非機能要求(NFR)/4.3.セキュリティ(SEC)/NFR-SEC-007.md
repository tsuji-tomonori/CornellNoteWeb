# NFR-SEC-007 脆弱性管理（修正SLA）

## Snow Card
- **Requirement ID**: NFR-SEC-007
- **Type**: Non-functional（Security/Vulnerability）
- **Description（INCOSE形式）**: 本システムは、利用するOS・ミドルウェア・ライブラリの脆弱性を継続的に検出し、重大度Criticalの脆弱性を7日以内に修正または緩和しなければならない。
- **Rationale**: 既知脆弱性の放置を防ぐため。
- **Fit Criterion（受入基準/検証）**: スキャン結果とチケット履歴で、Criticalが7日以内にクローズされている比率が100%であることを確認する（分析）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: セキュリティ／運用
- **Supporting Materials**: 脆弱性対応SLA、SBOM方針
- **Conflicts/Dependencies**: リリースフロー（NFR-DEV-002）に依存。
