package com.example.cornellnote.api;

import com.example.cornellnote.api.dto.NoteResponse;
import com.example.cornellnote.api.dto.NoteSummaryResponse;
import com.example.cornellnote.api.dto.NotebookResponse;
import com.example.cornellnote.api.dto.PublicNoteResponse;
import com.example.cornellnote.api.dto.TagResponse;
import com.example.cornellnote.api.service.MockDataLoader;
import java.time.Instant;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class MockDataLoaderTest {
  @Autowired
  private MockDataLoader mockDataLoader;

  @Test
  @DisplayName("loadNotebooks should parse notebook data")
  void loadNotebooks_shouldParseNotebookData() {
    List<NotebookResponse> notebooks = mockDataLoader.loadNotebooks();

    Assertions.assertThat(notebooks).hasSize(3);
    Assertions.assertThat(notebooks.get(0))
        .isEqualTo(new NotebookResponse(
            "nb-001",
            "プログラミング学習",
            Instant.parse("2025-01-10T09:00:00Z"),
            Instant.parse("2025-01-15T14:30:00Z")
        ));
    Assertions.assertThat(notebooks.get(1))
        .isEqualTo(new NotebookResponse(
            "nb-002",
            "読書メモ",
            Instant.parse("2025-01-05T10:00:00Z"),
            Instant.parse("2025-01-14T16:00:00Z")
        ));
    Assertions.assertThat(notebooks.get(2))
        .isEqualTo(new NotebookResponse(
            "nb-003",
            "会議メモ",
            Instant.parse("2025-01-08T11:00:00Z"),
            Instant.parse("2025-01-16T09:00:00Z")
        ));
  }

  @Test
  @DisplayName("loadTags should parse tag data")
  void loadTags_shouldParseTagData() {
    List<TagResponse> tags = mockDataLoader.loadTags();

    Assertions.assertThat(tags).hasSize(5);
    Assertions.assertThat(tags.get(0))
        .isEqualTo(new TagResponse(
            "tag-001",
            "JavaScript",
            Instant.parse("2025-01-10T09:00:00Z"),
            Instant.parse("2025-01-10T09:00:00Z")
        ));
    Assertions.assertThat(tags.get(4))
        .isEqualTo(new TagResponse(
            "tag-005",
            "復習",
            Instant.parse("2025-01-09T09:00:00Z"),
            Instant.parse("2025-01-09T09:00:00Z")
        ));
  }

  @Test
  @DisplayName("loadNoteSummaries should parse notes")
  void loadNoteSummaries_shouldParseNotes() {
    List<NoteSummaryResponse> notes = mockDataLoader.loadNoteSummaries();

    Assertions.assertThat(notes).hasSize(4);
    Assertions.assertThat(notes.get(0))
        .isEqualTo(new NoteSummaryResponse(
            "note-001",
            "JavaScriptの非同期処理",
            "nb-001",
            List.of(
                new TagResponse("tag-001", "JavaScript", null, null),
                new TagResponse("tag-004", "重要", null, null)
            ),
            "Promise、async/await、コールバック関数について学習。非同期処理の基本概念を整理...",
            Instant.parse("2025-01-15T10:00:00Z"),
            Instant.parse("2025-01-16T14:30:00Z")
        ));
  }

  @Test
  @DisplayName("loadNote should parse note data")
  void loadNote_shouldParseNoteData() {
    NoteResponse note = mockDataLoader.loadNote("001");

    Assertions.assertThat(note)
        .isEqualTo(new NoteResponse(
            "note-001",
            "JavaScriptの非同期処理",
            "# キーポイント\n\n- Promise とは？\n- async/await の使い方\n- コールバック地獄の回避\n- エラーハンドリング\n- 並列実行 vs 直列実行",
            "## 非同期処理の基本\n\nJavaScriptはシングルスレッドで動作するため、時間のかかる処理（API呼び出し、ファイル読み込みなど）をブロックせずに実行する仕組みが必要。\n\n### Promise\n\n```javascript\nconst promise = new Promise((resolve, reject) => {\n  // 非同期処理\n  if (success) {\n    resolve(result);\n  } else {\n    reject(error);\n  }\n});\n```\n\n### async/await\n\n```javascript\nasync function fetchData() {\n  try {\n    const response = await fetch('/api/data');\n    const data = await response.json();\n    return data;\n  } catch (error) {\n    console.error(error);\n  }\n}\n```\n\n### Promise.all（並列実行）\n\n```javascript\nconst results = await Promise.all([\n  fetch('/api/users'),\n  fetch('/api/posts')\n]);\n```",
            "JavaScriptの非同期処理は、シングルスレッドの制約を克服するための重要な概念。Promiseは非同期操作を表現するオブジェクトで、async/awaitはPromiseをより直感的に扱える構文。エラーハンドリングはtry-catchで行い、複数の非同期処理を並列実行したい場合はPromise.allを使用する。",
            "nb-001",
            List.of(
                new TagResponse("tag-001", "JavaScript", null, null),
                new TagResponse("tag-004", "重要", null, null)
            ),
            Instant.parse("2025-01-15T10:00:00Z"),
            Instant.parse("2025-01-16T14:30:00Z")
        ));
  }

  @Test
  @DisplayName("loadSharedNote should parse public note")
  void loadSharedNote_shouldParsePublicNote() {
    PublicNoteResponse note = mockDataLoader.loadSharedNote("abc123");

    Assertions.assertThat(note)
        .isEqualTo(new PublicNoteResponse(
            "note-001",
            "JavaScriptの非同期処理",
            "# キーポイント\n\n- Promise とは？\n- async/await の使い方\n- コールバック地獄の回避\n- エラーハンドリング\n- 並列実行 vs 直列実行",
            "## 非同期処理の基本\n\nJavaScriptはシングルスレッドで動作するため、時間のかかる処理（API呼び出し、ファイル読み込みなど）をブロックせずに実行する仕組みが必要。\n\n### Promise\n\n```javascript\nconst promise = new Promise((resolve, reject) => {\n  // 非同期処理\n  if (success) {\n    resolve(result);\n  } else {\n    reject(error);\n  }\n});\n```\n\n### async/await\n\n```javascript\nasync function fetchData() {\n  try {\n    const response = await fetch('/api/data');\n    const data = await response.json();\n    return data;\n  } catch (error) {\n    console.error(error);\n  }\n}\n```\n\n### Promise.all（並列実行）\n\n```javascript\nconst results = await Promise.all([\n  fetch('/api/users'),\n  fetch('/api/posts')\n]);\n```",
            "JavaScriptの非同期処理は、シングルスレッドの制約を克服するための重要な概念。Promiseは非同期操作を表現するオブジェクトで、async/awaitはPromiseをより直感的に扱える構文。エラーハンドリングはtry-catchで行い、複数の非同期処理を並列実行したい場合はPromise.allを使用する。",
            Instant.parse("2025-01-15T10:00:00Z")
        ));
  }
}
