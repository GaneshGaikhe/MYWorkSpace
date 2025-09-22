package com.example.backend.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;
}
