package com.example.expenseManager.auth.infraestructure.controller;

import com.example.expenseManager.auth.application.dto.AuthLoginUserRequest;
import com.example.expenseManager.auth.application.dto.AuthRegisterUserRequest;

import com.example.expenseManager.auth.application.service.UserAuthService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manager/auth")
public class AuthenticationController {

   @Autowired
   UserAuthService userAuthService;

   @PostMapping("/sign-up")
   public ResponseEntity<?> register(@RequestBody @Valid AuthRegisterUserRequest userRequest) {
      return new ResponseEntity<>(this.userAuthService.createUser(userRequest), HttpStatus.CREATED);
   }

   @PostMapping("/log-in")
   public ResponseEntity<?> login(@RequestBody @Valid AuthLoginUserRequest userRequest) {
      return new ResponseEntity<>(this.userAuthService.loginUser(userRequest), HttpStatus.OK);
   }
}
