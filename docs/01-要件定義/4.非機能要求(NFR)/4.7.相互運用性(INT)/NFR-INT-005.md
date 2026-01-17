# NFR-INT-005 コンテナ互換性（OCI準拠）

## Snow Card
- **Requirement ID**: NFR-INT-005
- **Type**: Non-functional（Portability）
- **Description（INCOSE形式）**: 本システムは、コンテナ化されたサービスについて、OCI準拠のコンテナイメージとしてビルドし、標準的なコンテナランタイムで実行可能でなければならない。
- **Rationale**: 実行環境の選択肢を確保し、移植性を向上させるため。
- **Fit Criterion（受入基準/検証）**: 生成されたイメージがOCI仕様に準拠し、定義したベースライン環境で起動・動作することを確認する（テスト＋検査）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 3 / 5
- **Priority**: Could
- **Originator**: プラットフォーム／開発
- **Supporting Materials**: コンテナ標準、ビルド手順
- **Conflicts/Dependencies**: マネージドPaaS依存が強い場合は適用範囲を明確化する。
