package com.example.expenseManager.personalFinance.domain.port.out.repositories;

import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ITransactionRepository {

   Transaction save(Transaction transaction);
   void deleteById(Long id);
   Optional<Transaction> findById(Long id);
   List<Transaction> findAll();
   BigDecimal summaryForTypeAndMonthly(TypeTransaction type, Long userId, int mes); //ingreso, gasto, entrada de ahorro o salida de ahorro.
   BigDecimal summaryAmountByType(TypeTransaction typeTransaction, String email);
    Page<Transaction> findAllPage(Pageable pageable);
}
