package com.example.cornellnote.api;

import com.example.cornellnote.api.dto.CreateNoteRequest;
import com.example.cornellnote.api.dto.UpdateNoteRequest;
import com.example.cornellnote.api.support.ApiTestSupport;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import javax.sql.DataSource;
import org.assertj.core.api.Assertions;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.ITable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBRider
@DBUnit(
    disableSequenceFiltering = true,
    caseInsensitiveStrategy = Orthography.LOWERCASE,
    schema = "TEST")
class NoteControllerTest extends ApiTestSupport {
  @Autowired private DataSource dataSource;

  @Test
  @DisplayName("UT-NOTE-007 listNotes should return summaries")
  @DataSet(value = "datasets/note-list.yml", disableConstraints = true)
  void listNotes_shouldReturnSummaries() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(
                        "/api/notes")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/notes-list.json", response);
  }

  @Test
  @DisplayName("UT-NOTE-001 createNote should persist note")
  @DataSet(value = "datasets/note-create.yml", disableConstraints = true)
  void createNote_shouldPersistNote() throws Exception {
    CreateNoteRequest request =
        new CreateNoteRequest(
            "新規ノート",
            "Cue",
            "Notes",
            "Summary",
            java.util.UUID.fromString("22222222-2222-2222-2222-222222222222"),
            java.util.List.of());

    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(
                        "/api/notes")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/notes-create.json", response);

    Connection connection = DataSourceUtils.getConnection(dataSource);
    try {
      IDatabaseConnection dbUnitConnection = new DatabaseConnection(connection, "TEST");
      ITable table = dbUnitConnection.createDataSet().getTable("notes");
      Assertions.assertThat(table.getRowCount()).isEqualTo(1);
    } finally {
      DataSourceUtils.releaseConnection(connection, dataSource);
    }
  }

  @Test
  @DisplayName("UT-NOTE-014 createNote should persist tags")
  @DataSet(value = "datasets/note-create-with-tags.yml", disableConstraints = true)
  void createNote_shouldPersistTags() throws Exception {
    CreateNoteRequest request =
        new CreateNoteRequest(
            "新規ノート",
            "Cue",
            "Notes",
            "Summary",
            java.util.UUID.fromString("22222222-2222-2222-2222-222222222222"),
            java.util.List.of(
                java.util.UUID.fromString("55555555-5555-5555-5555-555555555555"),
                java.util.UUID.fromString("88888888-8888-8888-8888-888888888888")));

    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(
                        "/api/notes")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/notes-create-tags.json", response);

    Connection connection = DataSourceUtils.getConnection(dataSource);
    try {
      IDatabaseConnection dbUnitConnection = new DatabaseConnection(connection, "TEST");
      ITable table = dbUnitConnection.createDataSet().getTable("note_tags");
      Assertions.assertThat(table.getRowCount()).isEqualTo(2);
    } finally {
      DataSourceUtils.releaseConnection(connection, dataSource);
    }
  }

  @Test
  @DisplayName("UT-NOTE-015 listNotes should return newest first")
  @DataSet(value = "datasets/note-list-order.yml", disableConstraints = true)
  void listNotes_shouldReturnNewestFirst() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(
                        "/api/notes")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/notes-list-order.json", response);
  }

  @Test
  @DisplayName("UT-NOTE-016 listNotes should handle preview length")
  @DataSet(value = "datasets/note-list-preview.yml", disableConstraints = true)
  void listNotes_shouldHandlePreviewLength() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(
                        "/api/notes")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    com.fasterxml.jackson.databind.JsonNode node = objectMapper.readTree(response);
    String preview = node.get("items").get(0).get("preview").asText();
    Assertions.assertThat(preview).hasSize(83);
    Assertions.assertThat(preview).endsWith("...");
    Assertions.assertThat(preview.substring(0, 80)).isEqualTo("b".repeat(80));
  }

  @Test
  @DisplayName("UT-NOTE-009 getNote should return note")
  @DataSet(value = "datasets/note-get.yml", disableConstraints = true)
  void getNote_shouldReturnNote() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(
                        "/api/notes/{noteId}", "11111111-2222-3333-4444-555555555555")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/notes-get.json", response);
  }

  @Test
  @DisplayName("UT-NOTE-002 updateNote should update fields")
  @DataSet(value = "datasets/note-update.yml", disableConstraints = true)
  void updateNote_shouldUpdateFields() throws Exception {
    UpdateNoteRequest request =
        new UpdateNoteRequest(
            "更新タイトル",
            "更新Cue",
            "更新Notes",
            "更新Summary",
            null,
            java.util.List.of(java.util.UUID.fromString("99999999-9999-9999-9999-999999999999")));

    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put(
                        "/api/notes/{noteId}", "33333333-3333-3333-3333-333333333333")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/notes-update.json", response);
  }

  @Test
  @DisplayName("UT-NOTE-018 updateNote should return not found")
  @DataSet(value = "datasets/note-update.yml", disableConstraints = true)
  void updateNote_shouldReturnNotFound() throws Exception {
    UpdateNoteRequest request =
        new UpdateNoteRequest(
            "更新タイトル",
            "更新Cue",
            "更新Notes",
            "更新Summary",
            null,
            java.util.List.of());

    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put(
                        "/api/notes/{noteId}", "99999999-9999-9999-9999-999999999999")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.header().exists("X-Trace-Id"))
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/error-not-found.json", response);
  }

  @Test
  @DisplayName("UT-NOTE-012 deleteNote should return no content")
  @DataSet(value = "datasets/note-delete.yml", disableConstraints = true)
  void deleteNote_shouldReturnNoContent() throws Exception {
    mockMvc
        .perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(
                    "/api/notes/{noteId}", "33333333-3333-3333-3333-333333333333")
                .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                .accept(org.springframework.http.MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent())
        .andExpect(MockMvcResultMatchers.content().string(""));
  }

  @Test
  @DisplayName("UT-NOTE-013 getNoteContent should return content")
  @DataSet(value = "datasets/note-get.yml", disableConstraints = true)
  void getNoteContent_shouldReturnContent() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(
                        "/api/notes/{noteId}/content", "11111111-2222-3333-4444-555555555555")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/notes-content.json", response);
  }

  @Test
  @DisplayName("UT-NOTE-010 getNote should return not found")
  @DataSet(value = "datasets/note-get.yml", disableConstraints = true)
  void getNote_shouldReturnNotFound() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(
                        "/api/notes/{noteId}", "99999999-9999-9999-9999-999999999999")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.header().exists("X-Trace-Id"))
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/error-not-found.json", response);
  }

  @Test
  @DisplayName("UT-NOTE-017 deleteNote should return not found")
  @DataSet(value = "datasets/note-get.yml", disableConstraints = true)
  void deleteNote_shouldReturnNotFound() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(
                        "/api/notes/{noteId}", "99999999-9999-9999-9999-999999999999")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.header().exists("X-Trace-Id"))
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/error-not-found.json", response);
  }
}
