# NFR-INT-001 標準プロトコルと認証方式

## Snow Card
- **Requirement ID**: NFR-INT-001
- **Type**: Non-functional（Interoperability）
- **Description（INCOSE形式）**: 本システムは、外部システム連携において、標準化されたプロトコルおよび認証方式（例：HTTPS、OIDC/OAuth2等）を使用しなければならない。
- **Rationale**: 連携容易性と保守性を確保するため。
- **Fit Criterion（受入基準/検証）**: インタフェース仕様書にプロトコル・認証方式が明記され、標準外方式は例外承認を要することを確認する（検査）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: アーキテクト
- **Supporting Materials**: API標準、セキュリティ標準
- **Conflicts/Dependencies**: 既存連携の制約と調整が必要。
