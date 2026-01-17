# NFR-COST-002 予算設定と通知

## Snow Card
- **Requirement ID**: NFR-COST-002
- **Type**: Non-functional（Cost Control）
- **Description（INCOSE形式）**: 本システムは、月次予算に対する消費状況を監視し、予算消費が80%および100%に達した時点で関係者へ通知しなければならない。
- **Rationale**: 予算超過の早期抑止のため。
- **Fit Criterion（受入基準/検証）**: 予算値と通知先が設定され、テスト通知が到達することを確認する（テスト＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: FinOps
- **Supporting Materials**: 予算計画、通知設計
- **Conflicts/Dependencies**: オートスケール（NFR-PS-004）と調整が必要。
