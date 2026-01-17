# NFR-DEV-003 段階的リリース（カナリア/ブルーグリーン）

## Snow Card
- **Requirement ID**: NFR-DEV-003
- **Type**: Non-functional（Deployability）
- **Description（INCOSE形式）**: 本システムは、本番リリースにおいて、段階的リリースを適用し、異常検知時に影響範囲を限定しなければならない。
- **Rationale**: 変更起因障害の影響最小化のため。
- **Fit Criterion（受入基準/検証）**: 段階的リリースの手順が確立し、カナリア中のSLI悪化で停止または切戻しできることを確認する（実演＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: SRE／開発
- **Supporting Materials**: デプロイ手順、SLO連携設計
- **Conflicts/Dependencies**: 監視（NFR-OPS-002）に依存。
