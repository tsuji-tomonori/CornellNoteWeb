# NFR-AV-007 高可用性フェイルオーバ訓練

## Snow Card
- **Requirement ID**: NFR-AV-007
- **Type**: Non-functional（Resilience/Operations）
- **Description（INCOSE形式）**: 本システムは、本番相当環境において、少なくとも四半期に1回、フェイルオーバおよび復旧手順の訓練を実施し、復旧時間と手順の妥当性を検証しなければならない。
- **Rationale**: 実運用で手順が形骸化することを防ぎ、障害対応の確実性を高めるため。
- **Fit Criterion（受入基準/検証）**: 訓練計画・結果レポートが四半期ごとに保存され、訓練で得られた改善事項がバックログ化されていること（検査＋分析）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: SRE／運用
- **Supporting Materials**: 訓練計画、訓練レポート
- **Conflicts/Dependencies**: DRテスト（NFR-AV-006）と重複しないよう範囲を定義する。
