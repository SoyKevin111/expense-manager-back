package com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;

   @NotBlank(message = "Not null or empty")
   String name;

   @NotBlank(message = "Not null or empty")
   String description;
}
