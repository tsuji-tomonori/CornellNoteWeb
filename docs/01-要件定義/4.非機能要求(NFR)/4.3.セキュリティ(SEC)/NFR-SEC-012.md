# NFR-SEC-012 ソフトウェアサプライチェーン保護（SBOM/署名/検証）

## Snow Card
- **Requirement ID**: NFR-SEC-012
- **Type**: Non-functional（Security/Supply Chain）
- **Description（INCOSE形式）**: 本システムは、ビルドされる成果物（コンテナイメージ等）に対してSBOMを生成し、成果物へ署名を付与し、デプロイ時に署名検証を実施しなければならない。
- **Rationale**: 依存関係改ざんや不正成果物の混入を防止するため。
- **Fit Criterion（受入基準/検証）**: 各リリース成果物にSBOMが紐付いて保管されていること、署名が付与されていること、検証に失敗した成果物はデプロイされないことをパイプラインログで確認する（検査＋テスト）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Should
- **Originator**: セキュリティ／開発リード
- **Supporting Materials**: CI/CD設計、SBOM方針
- **Conflicts/Dependencies**: CI/CD（NFR-DEV-002）と依存。
