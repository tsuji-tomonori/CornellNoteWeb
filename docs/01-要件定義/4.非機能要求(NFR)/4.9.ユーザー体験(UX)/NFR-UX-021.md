# NFR-UX-021 堅牢性（HTML/ARIA準拠）と支援技術互換

## Snow Card
- **Requirement ID**: NFR-UX-021
- **Type**: Non-functional（Maintainability/Compatibility｜Volere: 14 Maintainability and Support Requirements）
- **Description（INCOSE形式）**: 本システムは、HTML/ARIA等の仕様に準拠した実装を行い、主要な支援技術・主要ブラウザの組合せで操作可能であることを保証しなければならない。
- **Rationale**: 支援技術の解釈に依存した不具合を抑制し、将来の拡張やブラウザ更新に対する耐性を高めるため。
- **Fit Criterion（受入基準/検証）**: 主要画面に対して、(1)重大なHTML/ARIAの文法違反・禁止パターンが0件（lint/validator）、(2)スクリーンリーダー検証（例：NVDA+Firefox、VoiceOver+Safari、TalkBack+Chromeのいずれかを含む）で主要フローが完了できることを確認し、結果を記録する（テスト＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: フロントエンド／QA
- **Supporting Materials**: コーディング規約、ARIAガイド、対応ブラウザ/支援技術マトリクス、WCAG 2.2: 4.1.1, 4.1.2（参照）
- **Conflicts/Dependencies**: SPAフレームワークの実装差、サードパーティウィジェット、検証環境（端末/OS）の用意に依存。
