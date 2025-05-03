package com.example.expenseManager.core.application.exceptions.models;

public class UnauthorizedAccessException extends RuntimeException {
   public UnauthorizedAccessException(String message) {
      super(message);
   }
}
