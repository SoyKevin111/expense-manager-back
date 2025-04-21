package com.example.expenseManager.personalFinance.application.usecases;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import com.example.expenseManager.personalFinance.domain.port.in.usecases.ITransactionUseCase;
import com.example.expenseManager.personalFinance.domain.port.out.repositories.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionUseCase implements ITransactionUseCase {

   @Autowired
   ITransactionRepository transactionRepository;

   @Override
   public Transaction create(Transaction transaction) {
      try {
         return this.transactionRepository.save(transaction);
      } catch (Exception ex) {
         throw new ServerInternalError("Error creating transaction");
      }
   }

   @Override
   public void deleteById(Long id) {
      try {
         this.transactionRepository.deleteById(id);
      } catch (Exception e) {
         throw new ServerInternalError("Error deleting transaction");
      }
   }

   @Override
   public Transaction findById(Long id) {
      try {
         return this.transactionRepository.findById(id).orElseThrow(
            () -> new ServerInternalError("Transaction not found"));
      } catch (Exception e) {
         throw new ServerInternalError("Error finding transaction by id");
      }
   }

   @Override
   public List<Transaction> findAll() {
      try {
         return this.transactionRepository.findAll();
      } catch (Exception e) {
         throw new ServerInternalError("Error finding all transactions");
      }
   }

   @Override
   public BigDecimal summaryForTypeAndMonthly(TypeTransaction type, Long userId, int mes) {
      return this.transactionRepository.summaryForTypeAndMonthly(type, userId, mes);
   }
}
