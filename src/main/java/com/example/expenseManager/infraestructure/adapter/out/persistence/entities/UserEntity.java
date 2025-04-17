package com.example.expenseManager.infraestructure.adapter.out.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;

   @NotBlank(message = "Not null or empty")
   String name;

   @NotBlank(message = "Not null or empty")
   String identification;
}
