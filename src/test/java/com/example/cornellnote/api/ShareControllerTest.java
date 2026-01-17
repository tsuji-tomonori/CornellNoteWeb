package com.example.cornellnote.api;

import com.example.cornellnote.api.support.ApiTestSupport;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DBRider
@DBUnit(disableSequenceFiltering = true, caseInsensitiveStrategy = Orthography.LOWERCASE, schema = "TEST")
class ShareControllerTest extends ApiTestSupport {
  @Test
  @DisplayName("UT-SHARE-001 createShareLink should return link")
  @DataSet(value = "datasets/note-get.yml", disableConstraints = true)
  void createShareLink_shouldReturnLink() throws Exception {
    String response = mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/notes/{noteId}/share", "11111111-2222-3333-4444-555555555555")
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
  void getSharedNote_shouldReturnNote() throws Exception {
    String response = performGet("/api/share/{token}", "abc123")
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
    String response = performGet("/api/share/{token}", "invalid")
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.header().exists("X-Trace-Id"))
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andReturn()
        .getResponse()
        .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/error-share-not-found.json", response);
  }

  @Test
  @DisplayName("UT-SHARE-004 revokeShare should return no content")
  void revokeShare_shouldReturnNoContent() throws Exception {
    performDelete("/api/share/{token}", "abc123")
        .andExpect(MockMvcResultMatchers.status().isNoContent())
        .andExpect(MockMvcResultMatchers.content().string(""));
  }
}
