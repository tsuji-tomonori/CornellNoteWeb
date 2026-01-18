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
class SearchControllerTest extends ApiTestSupport {
  @Test
  @DisplayName("UT-SEARCH-001 searchNotes should return list")
  @DataSet(value = "datasets/note-list.yml", disableConstraints = true)
  void searchNotes_shouldReturnList() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(
                        "/api/search")
                    .param("q", "JavaScript")
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
  @DisplayName("UT-SEARCH-002 searchNotes should ignore missing tags")
  @DataSet(value = "datasets/note-list-missing-tag.yml", disableConstraints = true)
  void searchNotes_shouldIgnoreMissingTags() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(
                        "/api/search")
                    .param("q", "Missing")
                    .sessionAttr("userId", "11111111-1111-1111-1111-111111111111")
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/notes-list-missing-tag.json", response);
  }
}
