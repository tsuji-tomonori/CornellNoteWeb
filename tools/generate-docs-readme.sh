#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
DOCS_DIR="$(cd "${SCRIPT_DIR}/../docs" && pwd)"

DOCS_DIR="${DOCS_DIR}" python3 - <<'PY'
from __future__ import annotations

import os
from pathlib import Path

DOCS_DIR = Path(os.environ["DOCS_DIR"])
OUTPUT_PATH = DOCS_DIR / "README.md"

DESCRIPTIONS = {
    "01-要件定義": {
        "purpose": "プロジェクトの要件を整理し、合意形成の基礎にします。",
        "include": "ビジネス要求、利用者要求、制約、機能/非機能の一覧。",
        "exclude": "詳細設計、実装手順、運用手順。",
    },
    "02-基本設計": {
        "purpose": "実装前提となる全体設計を共有します。",
        "include": "構成方針、主要コンポーネント、データ設計の概要。",
        "exclude": "詳細設計、具体的な実装コード、運用手順。",
    },
    "ADR": {
        "purpose": "アーキテクチャ上の意思決定と理由を残します。",
        "include": "背景、選択肢、決定内容、影響範囲。",
        "exclude": "要件一覧や設計詳細の本文。",
    },
    "0.用語(UL)": {
        "purpose": "プロジェクト固有の用語定義を明確化します。",
        "include": "用語の定義、略語、参考表現。",
        "exclude": "要件や設計の説明本文。",
    },
    "1.プロジェクトの目的(PP)": {
        "purpose": "プロジェクトの目的と成功条件を明確にします。",
        "include": "背景、ゴール、スコープ、成功指標。",
        "exclude": "詳細な機能要求や設計方針。",
    },
    "2.プロジェクトの制約(PC)": {
        "purpose": "前提条件と制約を整理します。",
        "include": "予算、期限、法規制、技術的制約。",
        "exclude": "要件の実現手段や設計詳細。",
    },
    "3.機能要求(FR)": {
        "purpose": "機能として満たすべき要件を整理します。",
        "include": "ユーザー行動、入出力、例外条件、機能優先度。",
        "exclude": "非機能要求や内部実装詳細。",
    },
    "3.5.ノート検索(SEARCH)": {
        "purpose": "ノート検索機能の要件を明確化します。",
        "include": "検索対象、条件、表示要件、制約。",
        "exclude": "他機能の要件や検索実装の詳細。",
    },
    "3.9.共通(COMMON)": {
        "purpose": "複数機能に共通する要件を整理します。",
        "include": "共通UI/動作、共通入力規則、共通エラーハンドリング。",
        "exclude": "個別機能固有の要件。",
    },
    "4.非機能要求(NFR)": {
        "purpose": "品質特性として満たすべき要件を整理します。",
        "include": "可用性、性能、セキュリティ等の指標と基準。",
        "exclude": "機能要件や実装手順。",
    },
    "4.1.可用性(AV)": {
        "purpose": "可用性に関する要求を整理します。",
        "include": "稼働率目標、冗長化方針、復旧時間目標。",
        "exclude": "性能やセキュリティの要件。",
    },
    "4.2.性能(PS)": {
        "purpose": "性能に関する要求を整理します。",
        "include": "応答時間、同時接続数、負荷条件。",
        "exclude": "可用性やセキュリティの要件。",
    },
    "4.3.セキュリティ(SEC)": {
        "purpose": "セキュリティに関する要求を整理します。",
        "include": "認証/認可、データ保護、脆弱性対策。",
        "exclude": "UXや性能の要件。",
    },
    "4.4.プライバシー(PRC)": {
        "purpose": "プライバシー保護に関する要求を整理します。",
        "include": "個人情報の取扱い、保管期間、匿名化方針。",
        "exclude": "運用手順の詳細。",
    },
    "4.5.可観測性(OPS)": {
        "purpose": "可観測性に関する要求を整理します。",
        "include": "ログ、メトリクス、アラート基準。",
        "exclude": "具体的な監視設定の手順。",
    },
    "4.6.DevOps(DEV)": {
        "purpose": "DevOps運用に関する要求を整理します。",
        "include": "CI/CD要件、環境管理、リリース基準。",
        "exclude": "コードの実装詳細。",
    },
    "4.7.相互運用性(INT)": {
        "purpose": "外部連携や互換性の要求を整理します。",
        "include": "連携対象、データ形式、互換性条件。",
        "exclude": "連携実装の手順。",
    },
    "4.8.料金(COST)": {
        "purpose": "コストや料金に関する要求を整理します。",
        "include": "費用上限、課金条件、運用コスト制約。",
        "exclude": "性能最適化の手法。",
    },
    "4.9.ユーザー体験(UX)": {
        "purpose": "UXに関する要求を整理します。",
        "include": "操作性、アクセシビリティ、ユーザー満足基準。",
        "exclude": "内部実装や運用手順。",
    },
    "2.データ設計": {
        "purpose": "データ構造と関係性の前提を整理します。",
        "include": "エンティティ、関係、制約、命名方針。",
        "exclude": "マイグレーションや実装コード。",
    },
}


def link_label(text: str, relative_path: Path) -> str:
    return f"[{text}](<{relative_path.as_posix()}>)"


def describe_dir(dir_name: str) -> str:
    default = {
        "purpose": f"{dir_name} に関するドキュメントです。",
        "include": "記載内容を整理してください。",
        "exclude": "不要な内容は省いてください。",
    }
    return DESCRIPTIONS.get(dir_name, default)


def markdown_heading(level: int, title: str) -> str:
    return f"{'#' * level} {title}"


def emit_directory(dir_path: Path, level: int, lines: list[str]) -> None:
    lines.append(markdown_heading(level, dir_path.name))
    lines.append("")
    description = describe_dir(dir_path.name)
    lines.append(f"目的: {description['purpose']}")
    lines.append(f"書くべきこと: {description['include']}")
    lines.append(f"書かないこと: {description['exclude']}")
    lines.append("")

    files = sorted([p for p in dir_path.iterdir() if p.is_file() and p.suffix == ".md"])
    if files:
        for file_path in files:
            rel_path = Path("./") / file_path.relative_to(DOCS_DIR)
            lines.append(f"- {link_label(file_path.name, rel_path)}")
        lines.append("")

    subdirs = sorted([p for p in dir_path.iterdir() if p.is_dir()])
    for subdir in subdirs:
        emit_directory(subdir, level + 1, lines)


def main() -> None:
    lines: list[str] = []
    lines.append("# Docs")
    lines.append("")
    lines.append("このディレクトリ配下のドキュメントの目的と参照先をまとめます。")
    lines.append("")

    for top_dir in sorted([p for p in DOCS_DIR.iterdir() if p.is_dir()]):
        emit_directory(top_dir, 2, lines)

    OUTPUT_PATH.write_text("\n".join(lines).rstrip() + "\n", encoding="utf-8")


if __name__ == "__main__":
    main()
PY
