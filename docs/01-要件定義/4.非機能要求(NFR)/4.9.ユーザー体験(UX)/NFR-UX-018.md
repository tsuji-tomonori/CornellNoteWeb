# NFR-UX-018 時間制限とセッションタイムアウトの配慮

## Snow Card
- **Requirement ID**: NFR-UX-018
- **Type**: Non-functional（Usability/Accessibility｜Volere: 11 Usability and Humanity Requirements）
- **Description（INCOSE形式）**: 本システムは、時間制限またはセッションタイムアウトを設ける場合、利用者に事前に通知し、延長・停止・再開などの手段を提供しなければならない。
- **Rationale**: 読み上げや拡大表示などにより操作に時間がかかる利用者でも、手続きを中断されずに完了できるようにするため。
- **Fit Criterion（受入基準/検証）**: 時間制限がある機能について、(1)終了前に十分な猶予（例：少なくとも20秒以上）をもって通知する、(2)延長または再開の手段を提供する、(3)入力内容の喪失を最小化（自動保存等）することを、仕様レビュー＋動作確認で満たす（テスト＋検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Should
- **Originator**: プロダクト／バックエンド／セキュリティ
- **Supporting Materials**: セッション管理仕様、WCAG 2.2: 2.2.1（参照）
- **Conflicts/Dependencies**: セキュリティ（セッション寿命）と両立が必要、決済/外部連携のタイムアウト仕様に依存。
