package com.example.expenseManager.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
   Long id;
   String name;
   String description;
}
