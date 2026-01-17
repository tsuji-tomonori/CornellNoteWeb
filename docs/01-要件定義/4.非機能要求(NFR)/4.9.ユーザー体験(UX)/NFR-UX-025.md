# NFR-UX-025 アクセシビリティ不具合の受付と是正プロセス

## Snow Card
- **Requirement ID**: NFR-UX-025
- **Type**: Non-functional（Support/Accessibility｜Volere: 14 Maintainability and Support Requirements）
- **Description（INCOSE形式）**: 本システムは、アクセシビリティに関するフィードバック受付窓口を提供し、重大なアクセシビリティ不具合を定義した手順と期限で是正しなければならない。
- **Rationale**: 運用開始後の課題を継続的に改善し、利用不能状態を長期化させないため。
- **Fit Criterion（受入基準/検証）**: 本番環境において、(1)アクセシビリティ問い合わせ先（フォーム/メール等）が到達可能である、(2)重大度基準（例：主要フローを阻害=High）とSLA（例：Highは次回パッチまたはX営業日以内に回避策提示）を運用手順として定義し、(3)実際のチケットで追跡できることを確認する（検査）。
- **Customer Satisfaction**: 3 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: CS／QA／プロダクト
- **Supporting Materials**: 障害/問い合わせ運用手順、重大度定義、チケットテンプレート、改善バックログ
- **Conflicts/Dependencies**: 保守体制（人員/予算）、リリースサイクル、外部コンポーネント起因の不具合に依存。
