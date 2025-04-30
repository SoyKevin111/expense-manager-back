package com.example.expenseManager.user.infraestructure.adapter.out.persistence.repositories;

import com.example.expenseManager.user.domain.RoleEnum;
import com.example.expenseManager.user.infraestructure.adapter.out.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryPostgresql extends JpaRepository<RoleEntity, Long> {
   RoleEntity findByRoleEnum(RoleEnum roleEnum);
}
