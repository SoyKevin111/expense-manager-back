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
    BigDecimal findFinancialStatusByMonthly(TypeTransaction type, String email, int mes); //ingreso, gasto, entrada de ahorro o salida de ahorro.
    Page<Transaction> findAllPage(Pageable pageable);
    BigDecimal findAmountByType(TypeTransaction typeTransaction, String email);
    BigDecimal findCurrentBalance(String email); //INCOME
    BigDecimal findCurrentSavings(String email);
}
