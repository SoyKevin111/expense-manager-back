package com.example.expenseManager.domain.port.out;

import com.example.expenseManager.domain.models.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ITransactionRepository {

   Transaction save(Transaction transaction);
   int deleteById(Long id);
   Optional<Transaction> findById(Long id);
   List<Transaction> findAll();
   BigDecimal summaryForTypeAndMonthly(String type, Long userId, int mes); //ingreso, gasto, entrada de ahorro o salida de ahorro.
}
