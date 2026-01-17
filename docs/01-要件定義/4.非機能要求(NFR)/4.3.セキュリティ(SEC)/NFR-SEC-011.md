# NFR-SEC-011 WAFおよびDDoS防御

## Snow Card
- **Requirement ID**: NFR-SEC-011
- **Type**: Non-functional（Security/Network）
- **Description（INCOSE形式）**: 本システムは、インターネット公開エンドポイントに対して、WAFによる防御ルールおよびDDoS緩和策を適用しなければならない。
- **Rationale**: 一般的なWeb攻撃（OWASP Top 10等）と大量トラフィック攻撃への耐性を高めるため。
- **Fit Criterion（受入基準/検証）**: WAFが有効化され、少なくともSQLi/XSS等の基本ルールが適用されていること、DDoS対策が有効であることを設定証跡で確認する（検査）。模擬攻撃でブロック/検知が行われることを確認する（テスト）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Should
- **Originator**: セキュリティ／SRE
- **Supporting Materials**: WAFルール方針、ネットワーク設計
- **Conflicts/Dependencies**: 誤検知による可用性影響（NFR-AV-001）と調整が必要。
