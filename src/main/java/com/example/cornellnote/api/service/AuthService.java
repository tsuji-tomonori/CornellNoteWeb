package com.example.cornellnote.api.service;

import com.example.cornellnote.api.dto.AuthCredentials;
import com.example.cornellnote.api.dto.AuthSession;
import com.example.cornellnote.api.dto.PasswordResetRequest;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  public AuthSession signup(AuthCredentials request) {
    return new AuthSession(UUID.randomUUID(), request.email(), request.email().split("@")[0]);
  }

  public AuthSession login(AuthCredentials request) {
    return new AuthSession(UUID.randomUUID(), request.email(), request.email().split("@")[0]);
  }

  public void logout() {}

  public void requestPasswordReset(PasswordResetRequest request) {}
}
