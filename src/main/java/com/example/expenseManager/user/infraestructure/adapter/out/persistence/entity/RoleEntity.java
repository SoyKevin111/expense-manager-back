package com.example.expenseManager.user.infraestructure.adapter.out.persistence.entity;

import com.example.expenseManager.user.domain.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;

   @Column(name = "role_name")
   @Enumerated(EnumType.STRING)
   RoleEnum roleEnum;
}
