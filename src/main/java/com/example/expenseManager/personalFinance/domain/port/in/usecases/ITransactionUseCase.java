package com.example.expenseManager.personalFinance.domain.port.in.usecases;

import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ITransactionUseCase {
    Transaction save(Transaction transaction); //guardar cualquier tipo de transaccion
    Transaction findById(Long id);
    BigDecimal findFinancialStatusByMonthly(TypeTransaction type, String email, int mes); //Pie chart
    Page<Transaction> findAllPage(Pageable pageable); //transacciones
    BigDecimal findAmountByType(TypeTransaction typeTransaction, String email); //monto por tipo
    BigDecimal findCurrentBalance(String email); //saldo actual
    BigDecimal findAvailableSavings(String email); //ahorros disponibles
}
