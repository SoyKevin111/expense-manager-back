package com.example.expenseManager.user.infraestructure.adapter.out.persistence.repositories;

import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.infraestructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryPostgresql extends JpaRepository<UserEntity, Long> {
   boolean existsByEmail(String email);
   Optional<UserEntity> findByEmail(String email);
}
