package com.example.cornellnote.api.controller;

import com.example.cornellnote.api.dto.AuthCredentials;
import com.example.cornellnote.api.dto.AuthSession;
import com.example.cornellnote.api.dto.PasswordResetRequest;
import com.example.cornellnote.api.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/signup")
  public ResponseEntity<AuthSession> signup(
      @Valid @RequestBody AuthCredentials request, HttpSession session) {
    AuthSession authSession = authService.signup(request);
    session.setAttribute("userId", authSession.userId());
    return ResponseEntity.status(HttpStatus.CREATED).body(authSession);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthSession> login(
      @Valid @RequestBody AuthCredentials request, HttpSession session) {
    AuthSession authSession = authService.login(request);
    session.setAttribute("userId", authSession.userId());
    return ResponseEntity.ok(authSession);
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(HttpSession session) {
    authService.logout();
    session.invalidate();
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/password/reset")
  public ResponseEntity<Void> requestPasswordReset(
      @Valid @RequestBody PasswordResetRequest request) {
    authService.requestPasswordReset(request);
    return ResponseEntity.accepted().build();
  }
}
