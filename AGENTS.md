# AGENTS.md

このリポジトリには Spring Boot API、静的フロントエンド、Playwright E2E テスト、AWS CDK のインフラが含まれます。
このガイドは自律的なコーディングエージェント向けの運用ルールです。

## プロジェクト構成

- Backend (Java/Spring): `src/main/java/com/example/cornellnote`
- Backend テスト (JUnit): `src/test/java/com/example/cornellnote`
- Frontエンド資産: `src/main/resources/static`
- Mock フロント: `mock/`
- E2E テスト (Playwright): `tests/e2e`
- Infra (AWS CDK + Jest): `infra/`
- Taskfile ヘルパー: `Taskfile.yaml`

## ビルド・Lint・テストコマンド

専用の lint/format タスクはありません。Taskfile のタスクを使用します。

### Backend (Gradle / Spring Boot)

- ビルド: `task backend:build`
- アプリ起動: `task backend:run`
- 単体テスト: `task backend:test`
- フルチェック (JaCoCo 閾値含む): `task backend:check`
- 単一テストクラス: `task backend:test:class CLASS=com.example.cornellnote.api.NoteControllerTest`
- 単一テストメソッド: `task backend:test:method TEST=com.example.cornellnote.api.NoteControllerTest.testName`

注意:
- JaCoCo は `com.example.cornellnote.api.controller` と `com.example.cornellnote.api.service` で 95% 以上の行カバレッジを要求します。
- テスト基盤は JUnit 5 です。

### Frontend E2E (Playwright)

- 依存インストール (root): `task frontend:install`
- E2E 一括: `task e2e:test`
- E2E UI モード: `task e2e:ui`
- 単一 E2E ファイル: `task e2e:file FILE=tests/e2e/uc-flows.spec.ts`
- タイトル指定の単一 E2E: `task e2e:title FILE=tests/e2e/uc-flows.spec.ts TITLE="test title"`

Mock サーバー系:
- Mock サーバー起動: `task mock:serve`
- Mock 対象の Playwright 実行 (スナップショット更新): `task mock:test`

### Infra (AWS CDK / TypeScript)

`infra/` で実行します。

- 依存インストール: `task infra:install`
- ビルド: `task infra:build`
- テスト: `task infra:test`
- 単一 Jest ファイル: `task infra:test:file FILE=test/infra.test.ts`
- 単一 Jest テスト名: `task infra:test:name NAME="test name"`
- CDK synth: `task infra:synth`
- CDK deploy: `task infra:deploy`

### ドキュメント・ユーティリティ

- pre-commit 有効化: `task precommit`
- OpenAPI HTML 生成: `task openapi:build`
- OpenAPI HTML 配信: `task openapi:ui`
- docs README 生成: `task docs:readme`
- DB migration (dbmate): `task db:status`, `task db:up`, `task db:down`
- Docker build/up: `task docker:build`, `task docker:up`

## コードスタイル指針

### 全体

- 分かりやすく明示的なコードを優先し、過度な省略は避ける。
- メソッドは小さく保ち、Controller は Service へ委譲する。
- 可能な限り不変データを優先し、Java では `final` ローカル変数を使う。
- ロジックが非自明な場合を除き、新しいコメントは追加しない。
- 既存ファイルが非 ASCII を使っていない限り、コードとドキュメントは ASCII を維持する。

### Java (Spring Boot)

Imports
- 明示的な import を使い、ワイルドカードは避ける。
- import グルーピング: project -> javax/jakarta -> java -> third-party -> org.springframework。

Formatting
- ブロックは 2 スペースインデントで統一。
- 既存スタイルに合わせ、不要な trailing comma を追加しない。
- 長い引数リストは 1 行 1 引数で改行する。

Types と null
- 具体的な型と明示的なジェネリクスを使う。
- null を返さず、空コレクションや Optional 相当で返す。
- Request DTO のフィールドは null を前提にチェックする。

Naming
- クラス: `PascalCase`。
- メソッド/フィールド: `camelCase`。
- DTO: `*Request`, `*Response`。
- Repository: Spring Data 規約の `*Repository`。

Error handling
- `ApiException` 系 (`NotFoundException`, `BadRequestException` など) を投げる。
- `ApiExceptionHandler` が HTTP ステータスを変換する前提で設計する。
- 想定外エラーは汎用ハンドラで 500 + trace id に任せる。

Spring パターン
- Controller は薄く、入力検証と Service 呼び出しに限定。
- Service はビジネスロジックとトランザクション境界を担当 (`@Transactional`)。
- Repository へのアクセスは Service 経由にする。

### TypeScript (Infra CDK)

Imports
- ES module import を基本とし、名前付き import を使う。
- 外部モジュール -> 内部モジュールの順で並べる。

Formatting
- 2 スペースインデント。
- 文字列はダブルクォート。

Types
- `strict` が有効で暗黙の `any` を禁止。
- `any` は避け、props や引数は明示的に型定義する。
- Construct のフィールドは可能な限り `readonly`。

Naming
- Construct は `PascalCase`、再利用可能なものは `Component` で終える。
- Props インターフェースは `ComponentNameProps`。

Error handling
- CDK は fail fast。必須 props はコンストラクタで検証する。

### フロントエンド資産 (static JS)

- `src/main/resources/static/js` と `mock/js` の既存ルールに従う。
- 既存がフレームワーク利用の場合を除き、素の JS を維持する。
- 小さく集中した関数を優先し、不要なグローバル状態は避ける。

## リポジトリ固有ルール

- Cursor / GitHub Copilot の追加指示ファイルは見当たりません。
- pre-commit を使う場合は `task precommit` を尊重する。

## テスト追加時

- Backend テストは `src/test/java/...` (JUnit 5 + database rider)。
- Infra テストは `infra/test/**/*.test.ts` (Jest)。
- E2E テストは `tests/e2e` (Playwright)。

## API 変更時

- コントラクト変更時は `docs/03-詳細設計/2.API詳細/openapi.yaml` を更新する。
- 必要に応じて `task openapi:build` を再実行する。

## DB スキーマ変更時

- `migrations/` の dbmate migration を使う。
- `task db:status` で状態確認し、`task db:up` で適用する。

## 簡易チェック

- Backend 変更: `task backend:test`
- ユーザーフロー変更: `task e2e:test`
- Infra 変更: `task infra:test`
