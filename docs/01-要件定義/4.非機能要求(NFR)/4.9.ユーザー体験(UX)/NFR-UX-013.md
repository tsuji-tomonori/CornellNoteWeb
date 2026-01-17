# NFR-UX-013 ページ構造（タイトル・見出し・ランドマーク）とナビゲーション

## Snow Card
- **Requirement ID**: NFR-UX-013
- **Type**: Non-functional（Usability/Accessibility｜Volere: 11 Usability and Humanity Requirements）
- **Description（INCOSE形式）**: 本システムは、各画面に一意で説明的なページタイトルを付与し、見出し構造およびランドマーク等により情報構造をプログラム的に提供し、利用者が複数の方法で主要機能へ到達できるようにしなければならない。
- **Rationale**: スクリーンリーダー利用者やキーボード利用者が、目的の情報・機能へ効率的に移動できるようにするため。
- **Fit Criterion（受入基準/検証）**: 主要画面において、(1)ページタイトルが内容を表し一意である、(2)見出し階層が論理的で飛び級がない、(3)header/nav/main/footer等のランドマークが適切で重複しない、(4)ナビゲーション（メニュー、パンくず、検索等）により主要ページへ複数経路で到達できることを、スクリーンリーダーの見出し/ランドマーク一覧で確認する（検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: UX／情報設計
- **Supporting Materials**: IA（情報アーキテクチャ）資料、画面テンプレート、WCAG 2.2: 2.4.2, 2.4.6, 1.3.1, 2.4.5（参照）
- **Conflicts/Dependencies**: SPAでのタイトル更新忘れ、CMSテンプレート制約、動的コンテンツ構造に依存。
