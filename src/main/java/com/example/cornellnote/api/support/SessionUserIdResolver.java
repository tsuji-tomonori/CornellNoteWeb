package com.example.cornellnote.api.support;

import com.example.cornellnote.api.error.BadRequestException;
import com.example.cornellnote.api.error.UnauthorizedException;
import jakarta.servlet.http.HttpSession;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class SessionUserIdResolver {
  public UUID requireUserId(HttpSession session) {
    Object attribute = session.getAttribute("userId");
    if (attribute == null) {
      throw new UnauthorizedException("未認証です");
    }

    UUID userId = null;
    if (attribute instanceof UUID id) {
      userId = id;
    } else if (attribute instanceof String id) {
      if (id.isBlank()) {
        throw new UnauthorizedException("未認証です");
      }
      try {
        userId = UUID.fromString(id);
      } catch (IllegalArgumentException ex) {
        throw new BadRequestException("userIdが不正です", ex);
      }
    }

    if (userId == null) {
      throw new UnauthorizedException("未認証です");
    }
    return userId;
  }
}
