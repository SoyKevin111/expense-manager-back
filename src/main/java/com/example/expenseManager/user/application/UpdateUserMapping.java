package com.example.expenseManager.user.application;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.user.application.dto.request.UpdateUserRequest;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.out.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserMapping {

   @Autowired
   IUserRepository userRepository;

   public User toDomainModel(UpdateUserRequest updateUserRequest, Long userId) {
      User user = userRepository.findById(userId)
         .orElseThrow(() -> new ServerInternalError("User not found"));
      if (!(updateUserRequest.getName() == null || updateUserRequest.getName().isEmpty())) {
         user.setName(updateUserRequest.getName());
      }
      if (!(updateUserRequest.getEmail() == null || updateUserRequest.getEmail().isEmpty())) {
         user.setEmail(updateUserRequest.getEmail());
      }
      return user;
   }
}