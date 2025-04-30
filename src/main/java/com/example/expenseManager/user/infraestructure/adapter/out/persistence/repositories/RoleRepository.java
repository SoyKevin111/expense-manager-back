package com.example.expenseManager.user.infraestructure.adapter.out.persistence.repositories;

import com.example.expenseManager.core.application.mappers.EntityGeneralMapper;
import com.example.expenseManager.user.domain.Role;
import com.example.expenseManager.user.domain.RoleEnum;
import com.example.expenseManager.user.domain.port.out.IRoleRepository;
import com.example.expenseManager.user.infraestructure.adapter.out.persistence.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository implements IRoleRepository {

   @Autowired
   RoleRepositoryPostgresql roleRepository;
   @Autowired
   EntityGeneralMapper generalMapper;

   @Override
   public Role save(Role role) {
      RoleEntity roleEntity = this.generalMapper.toEntity(role, RoleEntity.class);
      return this.generalMapper.toDomain(this.roleRepository.save(roleEntity), Role.class);
   }

   @Override
   public Role findByRoleEnum(RoleEnum roleEnum) {
      return this.generalMapper.toDomain(this.roleRepository.findByRoleEnum(roleEnum), Role.class);
   }
}
