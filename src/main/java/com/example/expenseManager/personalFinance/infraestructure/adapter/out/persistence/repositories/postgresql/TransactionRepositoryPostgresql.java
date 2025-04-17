package com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.postgresql;

import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepositoryPostgresql extends JpaRepository<TransactionEntity, Long> {
}
