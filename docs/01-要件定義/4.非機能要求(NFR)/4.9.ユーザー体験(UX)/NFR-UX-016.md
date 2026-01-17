# NFR-UX-016 アクセシブルな認証（Accessible Authentication）

## Snow Card
- **Requirement ID**: NFR-UX-016
- **Type**: Non-functional（Security/Accessibility｜Volere: 15 Security Requirements）
- **Description（INCOSE形式）**: 本システムは、認証（サインイン、MFA、パスワード変更・リセット等）において、記憶・認知・識別に依存するテストを必須とせず、利用者がパスワードマネージャ等の支援機能を利用できるようにしなければならない。
- **Rationale**: 認知障害のある利用者や支援技術利用者が、認証という必須プロセスを完了できるようにするため。
- **Fit Criterion（受入基準/検証）**: 認証フローにおいて、(1)CAPTCHA等を用いる場合は少なくとも1つ以上のアクセシブルな代替手段を提供する、(2)パスワード入力欄でのコピー/ペースト、ブラウザ/OSの自動入力、パスワードマネージャの利用を妨げない、(3)画像識別やパズル等の認知テストのみで完了を要求しないことを、実機で確認する（テスト）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 5 / 5
- **Priority**: Must
- **Originator**: セキュリティ／UX／ID基盤
- **Supporting Materials**: 認証方式設計書（IdP含む）、WCAG 2.2: 3.3.8（参照）
- **Conflicts/Dependencies**: 不正ログイン対策（BOT対策）と両立が必要、外部IdP/SSO画面の制御範囲に依存。
