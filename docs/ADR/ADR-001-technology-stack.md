# ADR-001: 技術スタック採用

- Status: Accepted
- Date: 2026-01-17

## Context
本システムはCornellノート形式の作成/検索/共有/PDF出力を提供するWebアプリであり、
Webアプリとしての提供、ユーザ単位のデータ分離、外部IdP/メール委譲などの制約がある。
また、APIとUIの一体実装、セッションベースの認証、RDB中心のメタデータ管理が前提となる。

## Decision
サーバサイドの主要スタックとして以下を採用する。
- Java 21
- Spring Boot
- Thymeleaf
- PostgreSQL

## Consequences
- Java 21のLTSサポートにより、長期運用とセキュリティ更新が容易になる。
- Spring Bootの標準構成でMVC/セッション管理/DIが整備され、開発速度が向上する。
- Thymeleafでサーバサイドレンダリングを行い、認証/CSRFの整合を保ちやすい。
- PostgreSQLを採用し、整合性制約や検索の最適化を行いやすい。
- SPA専用構成や別言語のランタイムと比較して、構成の自由度は抑えられる。

## Alternatives
- Node.js + Express + React
- Java 21 + Spring Boot + SSR (Thymeleaf) + HTTP API
- .NET + Razor + SQL Server

## References
- docs/02-基本設計/0.全体概要/SYS-Overview.md
- docs/02-基本設計/1.アーキテクチャ/ARCH-Logical.md
- docs/02-基本設計/4.API設計/API-Auth.md
- docs/02-基本設計/2.データ設計/DATA-ERD.md
