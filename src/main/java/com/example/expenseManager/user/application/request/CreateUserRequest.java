package com.example.expenseManager.user.application.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {
   @NotBlank(message = "Name cannot be blank")
   String name;

   @NotBlank(message = "Identification cannot be blank")
   String identification;

   @Email(message = "Email should be valid")
   @NotBlank(message = "Email cannot be blank")
   String email;
}
