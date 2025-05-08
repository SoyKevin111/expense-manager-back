package com.example.expenseManager.personalFinance.application.validation;

import com.example.expenseManager.core.application.exceptions.models.ConflictValidationException;
import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import com.example.expenseManager.personalFinance.domain.port.out.repositories.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PersistenceTransactionValidator {

   @Autowired
   ITransactionRepository transactionRepository;


   public BigDecimal getSafeAmount(TypeTransaction type, String email) {
      BigDecimal amount = transactionRepository.summaryAmountByType(type, email);
      return amount != null ? amount : BigDecimal.ZERO;
   }

   public BigDecimal validateCurrentBalance(BigDecimal amount, String email) {
      BigDecimal income = getSafeAmount(TypeTransaction.INCOME, email);
      BigDecimal expense = getSafeAmount(TypeTransaction.EXPENSE, email);
      BigDecimal savingsIn = getSafeAmount(TypeTransaction.SAVINGS_IN, email);

      BigDecimal balanceAvailable = income.subtract(expense).subtract(savingsIn);

      if (balanceAvailable.compareTo(amount) < 0) {
         throw new ConflictValidationException("Insufficient funds for this transaction");
      }

      return balanceAvailable;
   }

   public BigDecimal validateAvailableSavings(BigDecimal amount, String email) {
      BigDecimal savingsIn = getSafeAmount(TypeTransaction.SAVINGS_IN, email);
      BigDecimal savingsOut = getSafeAmount(TypeTransaction.SAVINGS_OUT, email);

      BigDecimal savingsAvailable = savingsIn.subtract(savingsOut);

      if (savingsAvailable.compareTo(amount) < 0) {
         throw new ConflictValidationException("Insufficient funds for this transaction");
      }

      return savingsAvailable;
   }


}
