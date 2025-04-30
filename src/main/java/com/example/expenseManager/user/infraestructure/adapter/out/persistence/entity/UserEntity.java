package com.example.expenseManager.user.infraestructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

   @ManyToOne(cascade = CascadeType.REFRESH)
   @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
   RoleEntity role;
}
