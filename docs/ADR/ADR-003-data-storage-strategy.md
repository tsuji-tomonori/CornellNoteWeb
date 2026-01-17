# ADR-003: データ保管戦略（RDB + Object Storage）

- Status: Accepted
- Date: 2026-01-17

## Context
ノート本文/サマリ/キューなど大きめのテキストやPDFを扱うため、
RDBに全データを格納すると性能・コスト・拡張性の面で不利になる。
一方で、権限/検索/タグ付けなどメタデータの整合性は厳密に管理する必要がある。

## Decision
- メタデータ（User/Note/Tag/ShareToken/ExportJob等）はPostgreSQLに保持する。
- ノート本文/サマリ/キュー/PDFはObject Storageに保存し、RDBには参照のみ保持する。

## Consequences
- メタデータはRDBの制約で整合性を担保できる。
- 大容量データはObject Storageで保管し、APIペイロード制限や性能要件を満たしやすい。
- 参照不整合や削除連携の運用が必要になる。

## Alternatives
- すべてRDBに格納
- すべてObject Storageに格納し検索は外部検索エンジンに委譲

## References
- docs/02-基本設計/2.データ設計/DATA-DomainModel.md
- docs/02-基本設計/2.データ設計/DATA-ERD.md
- docs/02-基本設計/5.非機能設計/NFR-PerformanceDesign.md
- docs/02-基本設計/5.非機能設計/NFR-DataReliabilityDesign.md
