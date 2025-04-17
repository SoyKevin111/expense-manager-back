package com.example.expenseManager.infraestructure.adapter.out.persistence.repositories.postgresql;

import com.example.expenseManager.infraestructure.adapter.out.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryPostgresql extends JpaRepository<UserEntity, Long> {
}
