package com.example.expenseManager.personalFinance.application.mapping;

import com.example.expenseManager.personalFinance.application.dto.request.CreateTransactionRequest;
import com.example.expenseManager.personalFinance.domain.models.Category;
import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.port.in.usecases.ICategoryUseCase;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.in.IUserUseCase;
import com.example.expenseManager.user.domain.port.out.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class CreateTransactionMapping {

   @Autowired
   private IUserRepository userRepository;
   @Autowired
   ICategoryUseCase categoryUseCase;

   public Transaction toDomainModel(CreateTransactionRequest createTransactionRequest, String email) {
      User user = this.userRepository.findByEmail(email).orElseThrow(
         () -> new RuntimeException("User not found"));
      Category category = this.categoryUseCase.findById(createTransactionRequest.getCategoryId()).orElseThrow(
         () -> new RuntimeException("Category not found"));

      Transaction transaction = new Transaction();
      transaction.setUser(user);
      transaction.setCategory(category);
      transaction.setTypeTransaction(createTransactionRequest.getTypeTransaction());
      transaction.setAmount(createTransactionRequest.getAmount());
      transaction.setCreatedDateTime(LocalDateTime.now());

      return transaction;
   }
}
