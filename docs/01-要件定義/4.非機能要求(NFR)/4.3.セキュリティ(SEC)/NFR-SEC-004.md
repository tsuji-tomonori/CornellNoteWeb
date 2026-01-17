# NFR-SEC-004 通信の暗号化（TLS）

## Snow Card
- **Requirement ID**: NFR-SEC-004
- **Type**: Non-functional（Security/Crypto）
- **Description（INCOSE形式）**: 本システムは、外部および内部の通信において、TLS 1.2以上を用いて通信を暗号化しなければならない。
- **Rationale**: 盗聴・改ざん防止のため。
- **Fit Criterion（受入基準/検証）**: TLSバージョン/暗号スイート設定を確認し、スキャンツールでTLS1.2未満が不許可であることを確認する（テスト＋検査）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: セキュリティ
- **Supporting Materials**: 暗号ポリシー、設定証跡
- **Conflicts/Dependencies**: レガシークライアント互換との調整が必要な場合がある。
