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

   @Column(nullable = true)
   String name;

   @Column(nullable = true, unique = true)
   String identification;

   @Column(nullable = true, unique = true)
   @NotBlank(message = "Not null or empty")
   String email;
}
