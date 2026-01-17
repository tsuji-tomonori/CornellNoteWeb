package com.example.cornellnote.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {
  @DeleteMapping
  public ResponseEntity<Void> deleteAccount() {
    return ResponseEntity.noContent().build();
  }
}
