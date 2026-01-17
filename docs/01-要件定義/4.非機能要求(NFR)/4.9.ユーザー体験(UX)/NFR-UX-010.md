# NFR-UX-010 ポインター入力のターゲットサイズ

## Snow Card
- **Requirement ID**: NFR-UX-010
- **Type**: Non-functional（Look&Feel/Accessibility｜Volere: 10 Look and Feel Requirements）
- **Description（INCOSE形式）**: 本システムは、ポインター入力の操作対象（ボタン、リンク、チェックボックス等）のターゲットサイズを、例外を除き24×24CSSピクセル以上としなければならない。
- **Rationale**: 運動機能に制約のある利用者やモバイル利用者が、誤タップを減らして確実に操作できるようにするため。
- **Fit Criterion（受入基準/検証）**: デザインシステムの全コンポーネントに対して、(1)主要な操作対象が24×24CSS px以上である、(2)小さいターゲットを許容する場合はWCAG例外（間隔/同等手段/インライン等）に該当することを設計根拠として記録する、(3)実装後に主要画面で自動測定または設計レビューにより適合を確認する（検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: UX／デザインシステム
- **Supporting Materials**: コンポーネント寸法仕様、WCAG 2.2: 2.5.8（参照）
- **Conflicts/Dependencies**: 密度の高いツールバー/表、広告/埋込部品、既存デザインの再設計コストに依存。
