# ADR-004: 認証方式（セッション + HttpOnly Cookie）

- Status: Accepted
- Date: 2026-01-17

## Context
Webアプリとして提供し、CSRF対策を前提とした認証方式が求められる。
外部IdP（Google OAuth）は任意であり、標準ログインを必須とする必要がある。

## Decision
- サーバサイドセッションとHttpOnly Cookieを基本認証方式とする。
- APIはCSRF対策を実施する。
- Google OAuthは任意の拡張として実装する。

## Consequences
- トークンの漏えいリスクを抑えられ、セッション失効時の扱いも明確になる。
- CSRF対策の実装と運用が必須となる。
- モバイル/外部API利用時は追加の設計が必要になる可能性がある。

## Alternatives
- JWTベースのステートレス認証
- OAuth/OIDC専用ログイン

## References
- docs/02-基本設計/0.全体概要/SYS-Overview.md
- docs/02-基本設計/4.API設計/API-Auth.md
- docs/02-基本設計/4.API設計/API-Overview.md
