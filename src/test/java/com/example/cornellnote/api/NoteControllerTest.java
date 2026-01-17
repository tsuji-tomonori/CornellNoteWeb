package com.example.cornellnote.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.cornellnote.api.dto.CreateNoteRequest;
import com.example.cornellnote.api.dto.NoteResponse;
import com.example.cornellnote.api.dto.UpdateNoteRequest;
import com.example.cornellnote.api.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.ITable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@DBRider
@DBUnit(disableSequenceFiltering = true, caseInsensitiveStrategy = Orthography.LOWERCASE, schema = "TEST")
@Transactional
class NoteControllerTest {
  @Autowired
  private NoteService noteService;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private DataSource dataSource;

  @Test
  @DisplayName("UT-NOTE-001 createNote() should persist note")
  @DataSet(value = "datasets/note-create.yml", disableConstraints = true)
  void createNote_shouldPersistNote() throws Exception {
    // Arrange
    CreateNoteRequest request = new CreateNoteRequest(
        "新規ノート",
        "Cue",
        "Notes",
        "Summary",
        "22222222-2222-2222-2222-222222222222",
        List.of()
    );

    // Act
    NoteResponse response = noteService.createNote(request);
    String responseJson = objectMapper.writeValueAsString(response);

    // Assert
    JSONAssert.assertEquals(
        objectMapper.writeValueAsString(Map.of(
            "noteId", response.noteId(),
            "title", "新規ノート",
            "cue", "Cue",
            "notes", "Notes",
            "summary", "Summary",
            "notebookId", "22222222-2222-2222-2222-222222222222",
            "tags", List.of(),
            "createdAt", response.createdAt(),
            "updatedAt", response.updatedAt()
        )),
        responseJson,
        false
    );

    Connection connection = DataSourceUtils.getConnection(dataSource);
    try {
      IDatabaseConnection dbUnitConnection = new DatabaseConnection(connection, "TEST");
      ITable table = dbUnitConnection.createDataSet().getTable("notes");
      assertThat(table.getRowCount()).isEqualTo(1);
    } finally {
      DataSourceUtils.releaseConnection(connection, dataSource);
    }
  }

  @Test
  @DisplayName("UT-NOTE-002 updateNote() should update cue")
  @DataSet(value = "datasets/note-existing.yml", disableConstraints = true)
  void updateNote_shouldUpdateCue() throws Exception {
    // Arrange
    UpdateNoteRequest request = new UpdateNoteRequest(
        null,
        "更新Cue",
        null,
        null,
        null,
        null
    );

    // Act
    NoteResponse response = noteService.updateNote("33333333-3333-3333-3333-333333333333", request);
    String responseJson = objectMapper.writeValueAsString(response);

    // Assert
    JSONAssert.assertEquals(
        objectMapper.writeValueAsString(Map.of(
            "noteId", "33333333-3333-3333-3333-333333333333",
            "title", "初期ノート",
            "cue", "更新Cue",
            "notes", "既存の本文",
            "summary", "既存の要約",
            "notebookId", "22222222-2222-2222-2222-222222222222",
            "tags", List.of(
                Map.of("tagId", "55555555-5555-5555-5555-555555555555", "name", "重要")
            ),
            "createdAt", response.createdAt(),
            "updatedAt", response.updatedAt()
        )),
        responseJson,
        false
    );

    NoteResponse refreshed = noteService.getNote("33333333-3333-3333-3333-333333333333");
    assertThat(refreshed.cue()).isEqualTo("更新Cue");
  }
}
