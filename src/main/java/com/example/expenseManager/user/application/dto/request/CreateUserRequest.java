package com.example.expenseManager.user.application.dto.request;


import com.example.expenseManager.user.domain.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateUserRequest { //Only ADMIN

   String username;
   String email;
   String password;
   Role role;


}
