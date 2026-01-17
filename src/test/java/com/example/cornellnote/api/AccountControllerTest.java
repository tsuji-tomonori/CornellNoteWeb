package com.example.cornellnote.api;

import com.example.cornellnote.api.support.ApiTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AccountControllerTest extends ApiTestSupport {
  @Test
  @DisplayName("UT-AUTH-011 deleteAccount should return no content")
  void deleteAccount_shouldReturnNoContent() throws Exception {
    performDelete("/api/account")
        .andExpect(MockMvcResultMatchers.status().isNoContent())
        .andExpect(MockMvcResultMatchers.content().string(""));
  }
}
