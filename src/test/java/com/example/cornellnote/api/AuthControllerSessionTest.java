package com.example.cornellnote.api;

import com.example.cornellnote.api.dto.AuthCredentials;
import com.example.cornellnote.api.support.ApiTestSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerSessionTest extends ApiTestSupport {
  @Test
  @DisplayName("UT-AUTH-006 logout should invalidate session")
  void logout_shouldInvalidateSession() throws Exception {
    MockHttpSession session = new MockHttpSession();
    session.setAttribute("userId", "11111111-1111-1111-1111-111111111111");

    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/auth/logout").session(session))
        .andExpect(MockMvcResultMatchers.status().isNoContent())
        .andExpect(MockMvcResultMatchers.content().string(""));

    Assertions.assertThat(session.isInvalid()).isTrue();
  }

  @Test
  @DisplayName("UT-AUTH-003 login should store session")
  void login_shouldStoreSession() throws Exception {
    AuthCredentials request = new AuthCredentials("demo@example.com", "Pass1234!");
    MockHttpSession session = new MockHttpSession();

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/auth/login")
                .session(session)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isOk());

    Assertions.assertThat(session.getAttribute("userId")).isInstanceOf(java.util.UUID.class);
  }

  @Test
  @DisplayName("UT-AUTH-001 signup should store session")
  void signup_shouldStoreSession() throws Exception {
    AuthCredentials request = new AuthCredentials("demo@example.com", "Pass1234!");
    MockHttpSession session = new MockHttpSession();

    String response =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post("/api/auth/signup")
                    .session(session)
                    .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString(java.nio.charset.StandardCharsets.UTF_8);

    Assertions.assertThat(session.getAttribute("userId"))
        .as("signup should set session userId")
        .isNotNull();
    Assertions.assertThat(response).contains("userId");
  }
}
