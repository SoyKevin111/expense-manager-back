package com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories;

import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.port.out.repositories.ITransactionRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionRepository implements ITransactionRepository {
   @Override
   public Transaction save(Transaction transaction) {
      return null;
   }

   @Override
   public void deleteById(Long id) {
   }

   @Override
   public Optional<Transaction> findById(Long id) {
      return Optional.empty();
   }

   @Override
   public List<Transaction> findAll() {
      return List.of();
   }

   @Override
   public BigDecimal summaryForTypeAndMonthly(String type, Long userId, int mes) {
      return null;
   }
}
