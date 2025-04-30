package com.example.expenseManager.user.domain.port.out;

import com.example.expenseManager.user.domain.Role;
import com.example.expenseManager.user.domain.RoleEnum;

public interface IRoleRepository {
   Role save(Role role);
   Role findByRoleEnum(RoleEnum roleEnum);
}
