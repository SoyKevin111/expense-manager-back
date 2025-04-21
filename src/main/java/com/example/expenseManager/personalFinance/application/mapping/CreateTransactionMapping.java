package com.example.expenseManager.personalFinance.application.mapping;

import com.example.expenseManager.personalFinance.application.dto.request.CreateTransactionRequest;
import com.example.expenseManager.personalFinance.domain.models.Category;
import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.port.in.usecases.ICategoryUseCase;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.in.IUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CreateTransactionMapping {

   @Autowired
   private IUserUseCase userUseCase;
   @Autowired
   ICategoryUseCase categoryUseCase;

   public Transaction toDomainModel(CreateTransactionRequest createTransactionRequest) {
      User user = this.userUseCase.findById(createTransactionRequest.getUserId()).orElseThrow(
         () -> new RuntimeException("User not found"));
      Category category = this.categoryUseCase.findById(createTransactionRequest.getCategoryId()).orElseThrow(
         () -> new RuntimeException("Category not found"));

      Transaction transaction = new Transaction();
      transaction.setUser(user);
      transaction.setCategory(category);
      transaction.setTypeTransaction(createTransactionRequest.getTypeTransaction());
      transaction.setAmount(createTransactionRequest.getAmount());
      transaction.setCreatedAt(LocalDate.now());

      return transaction;
   }
}
