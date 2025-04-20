package com.example.expenseManager.user.application.mapping;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.core.application.mappers.RequestGeneralMapper;
import com.example.expenseManager.user.application.request.CreateUserRequest;
import com.example.expenseManager.user.application.request.UpdateUserRequest;
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
         user.setName(updateUserRequest.getName());
         user.setEmail(updateUserRequest.getEmail());
         return user;
      }
      throw new ServerInternalError("User not found");
   }
}