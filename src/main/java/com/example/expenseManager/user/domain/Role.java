package com.example.expenseManager.user.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
   Long id;
   RoleEnum roleEnum;

   public Role(RoleEnum roleEnum) {
      this.roleEnum = roleEnum;
   }
}
