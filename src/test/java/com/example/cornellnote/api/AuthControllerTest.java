package com.example.cornellnote.api;

import com.example.cornellnote.api.dto.AuthCredentials;
import com.example.cornellnote.api.support.ApiTestSupport;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest extends ApiTestSupport {
  @Test
  @DisplayName("UT-AUTH-001 signup should return session")
  void signup_shouldReturnSession() throws Exception {
    AuthCredentials request = new AuthCredentials("demo@example.com", "Pass1234!");

    String response =
        performPostJson("/api/auth/signup", request)
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/auth-signup.json", response);
    com.fasterxml.jackson.databind.JsonNode node = objectMapper.readTree(response);
    org.assertj.core.api.Assertions.assertThat(node.get("userId").asText())
        .as("signup should return generated userId")
        .isNotBlank();
  }

  @Test
  @DisplayName("UT-AUTH-004 login should validate request")
  void login_shouldValidateRequest() throws Exception {
    AuthCredentials request = new AuthCredentials("", "");

    String response =
        performPostJson("/api/auth/login", request)
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.header().exists("X-Trace-Id"))
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

    assertResponseMatches("expected/error-validation.json", response);
  }

  @Test
  @DisplayName("UT-AUTH-006 logout should return no content")
  void logout_shouldReturnNoContent() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/auth/logout"))
        .andExpect(MockMvcResultMatchers.status().isNoContent())
        .andExpect(MockMvcResultMatchers.content().string(""));
  }

  @Test
  @DisplayName("UT-AUTH-007 requestPasswordReset should accept")
  void requestPasswordReset_shouldAccept() throws Exception {
    String body = "{\"email\":\"demo@example.com\"}";

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/auth/password/reset")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
        .andExpect(MockMvcResultMatchers.status().isAccepted())
        .andExpect(MockMvcResultMatchers.content().string(""));
  }
}
