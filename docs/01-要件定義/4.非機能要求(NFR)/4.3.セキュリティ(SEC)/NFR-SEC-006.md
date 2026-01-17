# NFR-SEC-006 シークレット管理（平文埋め込み禁止）

## Snow Card
- **Requirement ID**: NFR-SEC-006
- **Type**: Non-functional（Security/Secrets）
- **Description（INCOSE形式）**: 本システムは、認証情報・APIキー・証明書等のシークレットを、アクセス制御と監査ログを備えた一元的な仕組みで保管し、アプリケーションコードおよび設定ファイルに平文で埋め込んではならない。
- **Rationale**: 漏えい経路（リポジトリ/ログ/設定）を排除するため。
- **Fit Criterion（受入基準/検証）**: リポジトリスキャンでシークレット混入がないこと、シークレットストア経由で参照されること、アクセスログが取得されることを確認する（テスト＋検査）。
- **Customer Satisfaction**: 5 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: セキュリティ／開発
- **Supporting Materials**: Secure SDLC標準、運用手順
- **Conflicts/Dependencies**: ローカル開発手段の整備が必要。
