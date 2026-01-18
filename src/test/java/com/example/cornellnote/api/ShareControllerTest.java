package com.example.cornellnote.api;

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
class ShareControllerTest extends ApiTestSupport {
  @Autowired private DataSource dataSource;

  @Test
  @DisplayName("UT-SHARE-001 createShareLink should return link")
  @DataSet(value = "datasets/note-get.yml", disableConstraints = true)
  void createShareLink_shouldReturnLink() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(
                        "/api/notes/{noteId}/share", "11111111-2222-3333-4444-555555555555")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/share-create.json", response);
  }

  @Test
  @DisplayName("UT-SHARE-002 getSharedNote should return note")
  @DataSet(value = "datasets/note-get.yml", disableConstraints = true)
  void getSharedNote_shouldReturnNote() throws Exception {
    String response =
        performGet("/api/share/{token}", "abc123")
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/share-get.json", response);
  }

  @Test
  @DisplayName("UT-SHARE-003 getSharedNote should return not found")
  void getSharedNote_shouldReturnNotFound() throws Exception {
    String response =
        performGet("/api/share/{token}", "invalid")
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.header().exists("X-Trace-Id"))
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/error-share-note-not-found.json", response);
  }


  @Test
  @DisplayName("UT-SHARE-007 createShareLink should return not found")
  @DataSet(value = "datasets/note-get.yml", disableConstraints = true)
  void createShareLink_shouldReturnNotFound() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(
                        "/api/notes/{noteId}/share", "99999999-9999-9999-9999-999999999999")
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
  @DisplayName("UT-SHARE-008 getSharedNote should return not found for missing note")
  @DataSet(value = "datasets/share-missing-note.yml", disableConstraints = true)
  void getSharedNote_shouldReturnNotFoundForMissingNote() throws Exception {
    String response =
        performGet("/api/share/{token}", "missing-note")
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.header().exists("X-Trace-Id"))
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/error-share-note-not-found.json", response);
  }

  @Test
  @DisplayName("UT-SHARE-009 revokeShare should return not found")
  @DataSet(value = "datasets/note-get.yml", disableConstraints = true)
  void revokeShare_shouldReturnNotFound() throws Exception {
    String response =
        performDelete("/api/share/{token}", "missing-token")
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.header().exists("X-Trace-Id"))
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/error-share-not-found.json", response);
  }

  @Test
  @DisplayName("UT-SHARE-005 revokeShare should keep revoked timestamp")
  @DataSet(value = "datasets/share-revoked.yml", disableConstraints = true)
  void revokeShare_shouldKeepRevokedTimestamp() throws Exception {
    performDelete("/api/share/{token}", "revoked123")
        .andExpect(MockMvcResultMatchers.status().isNoContent())
        .andExpect(MockMvcResultMatchers.content().string(""));

    Connection connection = DataSourceUtils.getConnection(dataSource);
    try {
      IDatabaseConnection dbUnitConnection = new DatabaseConnection(connection, "TEST");
      ITable table = dbUnitConnection.createDataSet().getTable("share_tokens");
      java.sql.Timestamp revokedAt = (java.sql.Timestamp) table.getValue(0, "revoked_at");
      Assertions.assertThat(revokedAt.toInstant().toString()).isEqualTo("2025-01-16T03:00:00Z");
    } finally {
      DataSourceUtils.releaseConnection(connection, dataSource);
    }
  }

  @Test
  @DisplayName("UT-SHARE-006 getSharedNote should return not found for expired")
  @DataSet(value = "datasets/share-expired.yml", disableConstraints = true)
  void getSharedNote_shouldReturnNotFoundForExpired() throws Exception {
    String response =
        performGet("/api/share/{token}", "expired123")
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.header().exists("X-Trace-Id"))
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/error-share-note-not-found.json", response);
  }
}
