package com.example.expenseManager.personalFinance.application.usecases;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import com.example.expenseManager.personalFinance.domain.port.in.usecases.ITransactionUseCase;
import com.example.expenseManager.personalFinance.domain.port.out.repositories.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionUseCase implements ITransactionUseCase {

   @Autowired
   ITransactionRepository transactionRepository;

   @Override
   public Transaction save(Transaction transaction) {

      switch (transaction.getTypeTransaction()) {
         case EXPENSE, SAVINGS_IN -> {
            BigDecimal result = this.findCurrentBalance(transaction.getUser().getEmail());
            if (result.compareTo(transaction.getAmount()) < 0) {
               throw new ServerInternalError("Insufficient funds for this transaction");
            }
         }
         case SAVINGS_OUT -> {
            BigDecimal result = this.findAvailableSavings(transaction.getUser().getEmail());
            if (result.compareTo(transaction.getAmount()) < 0) {
               throw new ServerInternalError("Insufficient funds for this transaction");
            }
         }
      }

      try {
         return this.transactionRepository.save(transaction);
      } catch (Exception ex) {
         throw new ServerInternalError("Error creating transaction");
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
   public BigDecimal findFinancialStatusByMonthly(TypeTransaction type, String email, int mes) {
      try {
         BigDecimal result = this.transactionRepository.summaryForTypeAndMonthly(type, email, mes);
         return result != null ? result : BigDecimal.ZERO;
      } catch (Exception e) {
         throw new ServerInternalError("Error finding financial status by monthly");
      }

   }

   @Override
   public Page<Transaction> findAllPage(Pageable pageable) {
      try {
         return this.transactionRepository.findAllPage(pageable);
      } catch (Exception e) {
         throw new ServerInternalError("Error finding all transactions with pagination");
      }
   }

   @Override
   public BigDecimal findAmountByType(TypeTransaction typeTransaction, String email) {
      try {
         return transactionRepository.summaryAmountByType(typeTransaction, email);
      } catch (Exception e) {
         throw new ServerInternalError("Error finding amount by type: " + typeTransaction);
      }
   }


   @Override
   public BigDecimal findCurrentBalance(String email) { //Total Saldo disponible
      BigDecimal income = this.transactionRepository.summaryAmountByType(TypeTransaction.INCOME, email);
      BigDecimal expense = this.transactionRepository.summaryAmountByType(TypeTransaction.EXPENSE, email);
      BigDecimal savingsIn = this.transactionRepository.summaryAmountByType(TypeTransaction.SAVINGS_IN, email);
      return income.subtract(expense).subtract(savingsIn);
   }

   @Override
   public BigDecimal findAvailableSavings(String email) { //Total Ahorros disponible
      BigDecimal savingsIn = this.transactionRepository.summaryAmountByType(TypeTransaction.SAVINGS_IN, email);
      BigDecimal savingsOut = this.transactionRepository.summaryAmountByType(TypeTransaction.SAVINGS_OUT, email);
      return savingsIn.subtract(savingsOut);
   }


}
/*


 */
