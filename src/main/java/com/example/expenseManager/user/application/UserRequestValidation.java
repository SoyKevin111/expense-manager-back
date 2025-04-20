package com.example.expenseManager.user.application;

import com.example.expenseManager.core.application.exceptions.models.ConflictValidationException;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.in.IUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRequestValidation {

   @Autowired
   IUserUseCase userUseCase;

   public void validateForCreate(User user) {
      if (this.userUseCase.existsByEmail(user.getEmail())) {
         throw new ConflictValidationException("Email already exists");
      }
      if (this.userUseCase.existsByIdentification(user.getIdentification())) {
         throw new ConflictValidationException("Identification already exists");
      }
   }

   public void validateForUpdate(User user) {
      User userResponse = this.userUseCase.findById(user.getId()).orElseThrow(() -> new ConflictValidationException("User not found"));
      if (!userResponse.getEmail().equals(user.getEmail()) //si emailObj != emailDb
         && this.userUseCase.existsByEmail(user.getEmail())) { //si emailDb existe en Db
         throw new ConflictValidationException("Email already exists");
      }
   }

}
