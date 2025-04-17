package com.example.expenseManager.domain.models;

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
}
