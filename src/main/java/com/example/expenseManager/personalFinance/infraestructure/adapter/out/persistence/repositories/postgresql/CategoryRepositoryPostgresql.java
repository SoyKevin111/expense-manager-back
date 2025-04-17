package com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.postgresql;

import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryPostgresql extends JpaRepository<CategoryEntity, Long> {
}
