package com.example.cornellnote.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthCredentials(@Email @NotBlank String email, @NotBlank String password) {}
