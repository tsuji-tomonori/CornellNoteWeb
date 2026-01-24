package com.example.cornellnote.api;

import com.example.cornellnote.api.support.ApiTestSupport;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
class ExportControllerTest extends ApiTestSupport {
  @Test
  @DisplayName("UT-EXPORT-001 requestExport should return job")
  @DataSet(value = "datasets/note-get.yml", disableConstraints = true)
  void requestExport_shouldReturnJob() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(
                        "/api/notes/{noteId}/export/pdf", "11111111-2222-3333-4444-555555555555")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isAccepted())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/export-request.json", response);
  }

  @Test
  @DisplayName("UT-EXPORT-003 getExportStatus should return status")
  void getExportStatus_shouldReturnStatus() throws Exception {
    String response =
        performGet("/api/exports/{exportId}", "exp-001")
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/export-status.json", response);
  }
}
