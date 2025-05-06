package com.example.expenseManager.personalFinance.application.usecases;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import com.example.expenseManager.personalFinance.domain.port.in.usecases.ITransactionUseCase;
import com.example.expenseManager.personalFinance.domain.port.out.repositories.ITransactionRepository;
import com.example.expenseManager.user.domain.port.out.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
   public BigDecimal findCurrentSavings(String email) { //Total Ahorros disponible
      try {
         BigDecimal savingsIn = this.transactionRepository.summaryAmountByType(TypeTransaction.SAVINGS_IN, email); //Ahorros
         BigDecimal savingsOut = this.transactionRepository.summaryAmountByType(TypeTransaction.SAVINGS_OUT, email); //Ahorros
         return (savingsIn != null ? savingsIn : BigDecimal.ZERO)
            .subtract(savingsOut != null ? savingsOut : BigDecimal.ZERO);
      } catch (Exception e) {
         throw new ServerInternalError("Error finding current balance");
      }
   }

   @Override
   public BigDecimal findCurrentBalance(String email) { //Total Saldo disponible
      try {
         BigDecimal income = this.transactionRepository.summaryAmountByType(TypeTransaction.INCOME, email); //Ingreso
         BigDecimal expense = this.transactionRepository.summaryAmountByType(TypeTransaction.EXPENSE, email); //se resta los gastos
         BigDecimal savingsIn = this.transactionRepository.summaryAmountByType(TypeTransaction.SAVINGS_IN, email); //se resta los ahorros
         return (income != null ? income : BigDecimal.ZERO)
            .subtract(expense != null ? expense : BigDecimal.ZERO)
            .subtract(savingsIn != null ? savingsIn : BigDecimal.ZERO);
      } catch (Exception e) {
         throw new ServerInternalError("Error finding current savings");
      }
   }

}
/*


 */
