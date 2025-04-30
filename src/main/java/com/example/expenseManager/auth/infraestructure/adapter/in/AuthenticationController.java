package com.example.expenseManager.auth.infraestructure.adapter.in;

import com.example.expenseManager.auth.application.dto.AuthLoginUserRequest;
import com.example.expenseManager.auth.application.dto.AuthRegisterUserRequest;
import com.example.expenseManager.auth.application.dto.AuthResponse;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("manager/auth")
public class AuthenticationController {

   @PostMapping("/sign-up")
   public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthRegisterUserRequest userRequest) {
      return null;
   }

   @PostMapping("/log-in")
   public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginUserRequest userRequest) {
      return null;
   }
}
