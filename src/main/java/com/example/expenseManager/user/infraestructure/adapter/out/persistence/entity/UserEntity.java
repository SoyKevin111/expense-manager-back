package com.example.expenseManager.user.infraestructure.adapter.out.persistence.entity;

import com.example.expenseManager.user.domain.RoleEnum;
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
   @NotBlank(message = "username not null or empty")
   String username;

   @Column(nullable = true, unique = true)
   @NotBlank(message = "Not null or empty")
   String email;

   @Column(nullable = true, unique = true)
   @NotBlank(message = "password not null or empty")
   String password;

   @Column(name = "role_name")
   @Enumerated(EnumType.STRING)
   RoleEnum role;
}
