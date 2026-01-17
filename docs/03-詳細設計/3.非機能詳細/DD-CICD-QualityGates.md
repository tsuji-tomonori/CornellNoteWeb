# CI/CD・品質ゲート（詳細）

## 対応NFR
- NFR-DEV-002, DEV-003, DEV-004, DEV-007, DEV-008
- NFR-SEC-007, SEC-012
- NFR-PS-006
- NFR-UX-023

## パイプライン構成
- Lint/静的解析: ESLint/TypeCheck、重大違反 0件
- 依存関係スキャン: Trivy/Snyk、Critical/High 0件
- 自動テスト
  - Unit: カバレッジ 80%以上
  - Integration: 主要API 100%通過
  - E2E: 主要ユースケース 100%通過
- 性能試験: リリースゲート（p95 <= 300ms）
- アクセシビリティ自動検査: axe-core 重大/深刻 0件
- SBOM生成: CycloneDX（JSON）
- 成果物署名: cosign
- デプロイ時検証: 署名検証・SBOM一致チェック

## 品質ゲート（ブランチ保護）
- mainへのマージ条件
  - Lint/型検査: 100%成功
  - Unit/Integration/E2E: 100%成功
  - カバレッジ: 80%以上
  - SAST/依存関係スキャン: Critical/High 0件
  - アクセシビリティ: 重大/深刻 0件

## 段階的リリース/ロールバック
- カナリア
  - 10%リリース 30分観測
  - 50%リリース 30分観測
  - 100%リリース
- 自動停止/切戻し条件
  - 5xx+timeout >= 1.0% が5分継続
  - p95 latency >= 600ms が5分継続
- ロールバック訓練
  - 四半期に1回
  - 30分以内に旧版へ復旧
