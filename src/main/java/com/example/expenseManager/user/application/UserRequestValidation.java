package com.example.expenseManager.user.application;

import com.example.expenseManager.core.application.exceptions.models.ConflictValidationException;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.out.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRequestValidation {

   @Autowired
   IUserRepository userRepository;

   public void validateForCreate(User user) {
      if (this.userRepository.existsByEmail(user.getEmail())) {
         throw new ConflictValidationException("Email already exists");
      }
      if (this.userRepository.existsByIdentification(user.getIdentification())) {
         throw new ConflictValidationException("Identification already exists");
      }
   }

   public void validateForUpdate(User user) {
      User userResponse = this.userRepository.findById(user.getId()).orElseThrow(() -> new ConflictValidationException("User not found"));
      if (!userResponse.getEmail().equals(user.getEmail()) //si emailObj != emailDb
         && this.userRepository.existsByEmail(user.getEmail())) { //si emailDb existe en Db
         throw new ConflictValidationException("Email already exists");
      }
   }

}
