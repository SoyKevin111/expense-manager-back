package com.example.expenseManager.auth.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthLoginUserRequest {

   @Email(message = "Email is not valid.")
   @NotBlank(message = "Email cannot be blank.")
   String email;
   @NotBlank
   @Size(max = 20, message = "Password must not exceed 20 characters.")
   String password;
}
