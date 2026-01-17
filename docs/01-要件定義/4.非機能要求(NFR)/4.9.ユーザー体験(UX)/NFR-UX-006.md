# NFR-UX-006 色のみに依存しない表現とコントラスト

## Snow Card
- **Requirement ID**: NFR-UX-006
- **Type**: Non-functional（Look&Feel/Accessibility｜Volere: 10 Look and Feel Requirements）
- **Description（INCOSE形式）**: 本システムは、情報伝達を色のみに依存せず、テキストおよびUIコンポーネントについてWCAG 2.2 AA相当のコントラスト要件を満たさなければならない。
- **Rationale**: 色覚多様性のある利用者や低視力の利用者が、状態・強調・操作対象を識別できるようにするため。
- **Fit Criterion（受入基準/検証）**: 主要画面において、(1)通常テキストはコントラスト比4.5:1以上、(2)大きなテキストは3:1以上、(3)入力枠/ボタン/アイコン等の非テキストUIは3:1以上、(4)エラー等の状態は色以外（アイコン/テキスト/パターン等）でも識別可能であることを、デザインレビュー＋自動コントラスト検査＋抜取手動確認で満たす（テスト＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: UX／デザインシステム
- **Supporting Materials**: カラートークン定義、UIコンポーネント仕様、WCAG 2.2: 1.4.1, 1.4.3, 1.4.11（参照）
- **Conflicts/Dependencies**: ブランドガイドライン、ダークモード/高コントラストモード対応、画像内テキストの扱いに依存。
