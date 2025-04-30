package com.example.expenseManager.user.application.validation;

public interface ICreateUserValidation {
   void validateProperty(String username, String password, String email);
   String validateRole(String role);
}
