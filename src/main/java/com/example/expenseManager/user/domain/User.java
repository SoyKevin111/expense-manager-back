package com.example.expenseManager.user.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
   Long id;
   String name;
   String identification;
   String email;
}
