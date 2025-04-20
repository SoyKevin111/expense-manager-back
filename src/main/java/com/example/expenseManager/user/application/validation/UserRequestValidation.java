package com.example.expenseManager.user.application.validation;

import com.example.expenseManager.core.application.exceptions.models.ConflictValidationException;
import com.example.expenseManager.user.application.request.CreateUserRequest;
import com.example.expenseManager.user.application.request.UpdateUserRequest;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.out.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRequestValidation {

   @Autowired
   IUserRepository userRepository;

   public void validateCreate(CreateUserRequest createUserRequest) {
      if (this.userRepository.existsByEmail(createUserRequest.getEmail())) {
         throw new ConflictValidationException("Email already exists");
      }
      if (this.userRepository.existsByIdentification(createUserRequest.getIdentification())) {
         throw new ConflictValidationException("Identification already exists");
      }
   }

   public void validateUpdate(UpdateUserRequest updateUserRequest, Long userId) {
      User userResponse = this.userRepository.findById(userId).orElseThrow(() -> new ConflictValidationException("User not found"));
      if (!userResponse.getEmail().equals(updateUserRequest.getEmail()) //si emailObj != emailDb
         && this.userRepository.existsByEmail(updateUserRequest.getEmail())) { //si emailDb existe en Db
         throw new ConflictValidationException("Email already exists");
      }
   }


}
