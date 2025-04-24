package com.example.expenseManager.personalFinance.domain.port.in.usecases;

import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionUseCase {
    Transaction create(Transaction transaction);
    void deleteById(Long id);
    Transaction findById(Long id);
    List<Transaction> findAll();
    BigDecimal summaryForTypeAndMonthly(TypeTransaction type, Long userId, int mes); //ingreso, gasto, entrada de ahorro o salida de ahorro.
    Page<Transaction> findAllPage(Pageable pageable);
}
