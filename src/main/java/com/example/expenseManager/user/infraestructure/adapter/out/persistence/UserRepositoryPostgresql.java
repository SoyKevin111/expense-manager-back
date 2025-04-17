package com.example.expenseManager.user.infraestructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryPostgresql extends JpaRepository<UserEntity, Long> {
}
