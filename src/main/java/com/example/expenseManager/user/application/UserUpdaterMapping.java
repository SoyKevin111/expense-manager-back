package com.example.expenseManager.user.application;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.user.application.dto.request.UpdateUserRequest;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.out.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUpdaterMapping {

   @Autowired
   IUserRepository userRepository;

   public User userUpdater(UpdateUserRequest updateUserRequest, Long userId) {
      Optional<User> userOptional = userRepository.findById(userId);
      if (userOptional.isPresent()) {
         User user = userOptional.get();
         if (!(updateUserRequest.getName() == null || updateUserRequest.getName().isEmpty())) {
            user.setName(updateUserRequest.getName());
         }
         if (!(updateUserRequest.getEmail() == null || updateUserRequest.getEmail().isEmpty())) {
            user.setEmail(updateUserRequest.getEmail());
         }
         return user;
      }
      throw new ServerInternalError("User not found");
   }
}