package com.example.expenseManager.user.application.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {
   @NotBlank(message = "Name cannot be blank")
   @Size(min = 5, max = 40, message = "Name must be between 3and 50 characters")
   String name;

   @NotBlank(message = "Identification cannot be blank")
   @Size(min = 10, max = 10, message = "Identification must be 10 characters")
   String identification;

   @Email(message = "Email should be valid")
   @NotBlank(message = "Email cannot be blank")
   String email;
}
