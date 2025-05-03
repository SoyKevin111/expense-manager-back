package com.example.expenseManager.auth.application.dto;

import com.example.expenseManager.user.domain.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthRegisterUserRequest {
   @NotBlank(message = "Username cannot be blank")
   @Size(min = 5, max = 30, message = "Username must be between 5 and 30 characters")
   private String username;

   @NotBlank(message = "Email cannot be blank")
   @Email(message = "Email should be valid")
   @Size(max = 50, message = "Email must be less than 50 characters")
   private String email;

   @NotBlank(message = "Password cannot be blank")
   @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
   private String password;

   @JsonProperty("role")
   private RoleEnum role;
}

/*
{
    "username": "string",
    "email": "string",
    "password": "string",
    "role": "USER"
}
 */
