package com.example.expenseManager.user.application.validation;

import com.example.expenseManager.user.application.dto.request.CreateUserRequest;
import com.example.expenseManager.user.domain.Role;
import com.example.expenseManager.user.domain.RoleEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CreateUserValidator implements ICreateUserValidation {

   public CreateUserRequest validate(CreateUserRequest createUserRequest) {
      this.validateProperty(createUserRequest.getUsername(),
         createUserRequest.getPassword(),
         createUserRequest.getEmail());
      String roleEnum = this.validateRole(createUserRequest.getRole().getRoleEnum().name());
      createUserRequest.setRole(new Role(RoleEnum.valueOf(roleEnum)));
      return createUserRequest;
   }

   @Override
   public void validateProperty(String username, String password, String email) {
      // Validar username
      if (username == null || username.isBlank()) {
         throw new IllegalArgumentException("Username cannot be blank");
      }
      if (username.length() < 5 || username.length() > 30) {
         throw new IllegalArgumentException("Username must be between 5 and 30 characters");
      }

      // Validar email
      if (email == null || email.isBlank()) {
         throw new IllegalArgumentException("Email cannot be blank");
      }
      if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
         throw new IllegalArgumentException("Email should be valid");
      }

      // Validar password
      if (password == null || password.isBlank()) {
         throw new IllegalArgumentException("Password cannot be blank");
      }
      if (password.length() < 8 || password.length() > 20) {
         throw new IllegalArgumentException("Password must be between 8 and 20 characters");
      }

   }

   // Validar role
   @Override
   public String validateRole(String role) {
      if (role == null || role.isBlank()) {
         return new Role(RoleEnum.USER).toString(); //si no se pasa un rol, se asigna el rol por defecto USER
      }
      try { //si no pertece a un rol valido, lanza una excepcion;
         RoleEnum roleEnum = RoleEnum.valueOf(role.toUpperCase());
      } catch (IllegalArgumentException e) {
         String validRoles = String.join(", ", Arrays.stream(RoleEnum.values())
            .map(Enum::name)
            .toArray(String[]::new));
         throw new IllegalArgumentException("Invalid role: " + role + ". Valid roles are: " + validRoles);
      }
      return role; //si pertece a algun rol valido, se retorna normalmente
   }
}
