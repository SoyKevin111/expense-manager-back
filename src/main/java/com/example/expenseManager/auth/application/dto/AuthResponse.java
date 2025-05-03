package com.example.expenseManager.auth.application.dto;

public record AuthResponse(String email,
                           String message,
                           String jwt,
                           boolean status) {
}
