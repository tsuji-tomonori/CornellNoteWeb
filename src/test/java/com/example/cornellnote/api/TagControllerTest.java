package com.example.cornellnote.api;

import com.example.cornellnote.api.support.ApiTestSupport;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
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
class TagControllerTest extends ApiTestSupport {
  private MockHttpSession session;

  @AfterEach
  void tearDown() {
    if (session != null) {
      session.invalidate();
    }
  }

  @Test
  @DisplayName("UT-ORG-003 listTags should return list")
  @DataSet(value = "datasets/notebook-tags.yml", disableConstraints = true)
  void listTags_shouldReturnList() throws Exception {
    session = new MockHttpSession();
    session.setAttribute("userId", "11111111-1111-1111-1111-111111111111");

    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/tags")
                    .session(session)
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/tags-list.json", response);
  }

  @Test
  @DisplayName("UT-ORG-003 listTags should require session")
  @DataSet(value = "datasets/notebook-tags.yml", disableConstraints = true)
  void listTags_shouldRequireSession() throws Exception {
    String response =
        mockMvc
            .perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/tags")
                    .accept(org.springframework.http.MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
            .andExpect(MockMvcResultMatchers.header().exists("X-Trace-Id"))
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/error-unauthorized.json", response);
  }
}
