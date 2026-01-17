# NFR-UX-024 サードパーティ/クラウドサービスのアクセシビリティ担保

## Snow Card
- **Requirement ID**: NFR-UX-024
- **Type**: Non-functional（Operational/Accessibility｜Volere: 13 Operational and Environmental Requirements）
- **Description（INCOSE形式）**: 本システムは、サードパーティUIコンポーネントまたはクラウドサービス（IdP、チャット、決済等）を組み込む場合、当該機能がWCAG 2.2 AA相当を満たすことを事前に確認し、満たさない場合は代替手段または緩和策を提供しなければならない。
- **Rationale**: 自社で制御できない部分がアクセシビリティ適合のボトルネックになることを防ぐため。
- **Fit Criterion（受入基準/検証）**: 導入する外部コンポーネント/サービスについて、(1)提供元のアクセシビリティ声明/VPAT等を取得する、(2)主要フローで自社の検査（自動＋手動）を実施し重大な不適合がないことを確認する、(3)不適合がある場合は代替導線・回避策・契約上の改善要請のいずれかを定義して記録する（検査）。
- **Customer Satisfaction**: 4 / 5
- **Customer Dissatisfaction**: 4 / 5
- **Priority**: Must
- **Originator**: アーキテクト／調達／UX
- **Supporting Materials**: 外部サービス一覧、評価チェックリスト、契約/調達基準
- **Conflicts/Dependencies**: ベンダー選定自由度、契約条件、外部画面（リダイレクト先）の制御範囲に依存。
