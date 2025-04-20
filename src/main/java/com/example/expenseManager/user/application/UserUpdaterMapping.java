package com.example.expenseManager.user.application;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.user.application.dto.request.UpdateUserRequest;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.in.IUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUpdaterMapping {

   @Autowired
   IUserUseCase userUseCase;

   public User userUpdater(UpdateUserRequest updateUserRequest, Long userId) {
      User user = userUseCase.findById(userId)
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