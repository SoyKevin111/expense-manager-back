package com.example.expenseManager.core.application.exceptions.models;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConflictValidationException extends RuntimeException{
   private LocalDateTime timestamp;
   private HttpStatus statusRef;
   private String error;
   private String message;

   public ConflictValidationException(String message) {
      this.message = message;
   }
}
