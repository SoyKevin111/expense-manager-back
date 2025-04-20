package com.example.expenseManager.user.infraestructure.adapter.out.persistence;

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

   @NotBlank(message = "Not null or empty")
   String email;
}
