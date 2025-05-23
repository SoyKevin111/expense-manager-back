package com.example.expenseManager.user.application;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.user.domain.RoleEnum;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.in.IUserUseCase;
import com.example.expenseManager.user.domain.port.out.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserUseCase implements IUserUseCase {

   @Autowired
   private IUserRepository userRepository;
   @Autowired
   UserRequestValidation requestValidation;

   @Override
   public User save(User user) {
      if (user.getId() == null || user.getId().toString().isEmpty()) { //create
         this.requestValidation.validateForCreate(user);
      } else { //update
         this.requestValidation.validateForUpdate(user);
      }

      try {
         if (user.getRole() == null) {
            user.setRole(RoleEnum.USER);
         }
         return userRepository.save(user);
      } catch (Exception e) {
         log.error(e.getMessage());
         throw new ServerInternalError(
            user.getId() == null || user.getId().toString().isEmpty()
               ? "Error creating user"
               : "Error updating user");
      }
   }

   @Override
   public void delete(Long id) {
      try {
         userRepository.deleteById(id);
      } catch (Exception e) {
         log.error(e.getMessage());
         throw new ServerInternalError("Error deleting user");
      }
   }

   @Override
   public Optional<User> findById(Long id) {
      try {
         return userRepository.findById(id);
      } catch (Exception e) {
         log.error(e.getMessage());
         throw new ServerInternalError("Error finding user by id");
      }
   }

   @Override
   public List<User> findAll() {
      try {
         return userRepository.findAll();
      } catch (Exception e) {
         log.error(e.getMessage());
         throw new ServerInternalError("Error finding all users");
      }
   }

   @Override
   public User updateProfile(User user) {
      try {
         return this.userRepository.save(user);
      } catch (Exception e) {
         log.error(e.getMessage());
         throw new ServerInternalError("Error updating user profile");
      }
   }

   @Override
   public User findByEmail(String email) {
      try {
         return this.userRepository.findByEmail(email)
            .orElseThrow(() -> new ServerInternalError("User not found"));
      } catch (Exception e) {
         log.error(e.getMessage());
         throw new ServerInternalError("Error finding user by email");
      }
   }

}
