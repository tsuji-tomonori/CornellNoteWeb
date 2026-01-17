package com.example.cornellnote.api.support;

import com.example.cornellnote.api.error.UnauthorizedException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionUserIdResolver {
  public String requireUserId(HttpSession session) {
    String userId = session.getAttribute("userId") instanceof String id ? id : null;
    if (userId == null || userId.isBlank()) {
      throw new UnauthorizedException("未認証です");
    }
    return userId;
  }
}
