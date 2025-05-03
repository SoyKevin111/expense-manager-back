package com.example.expenseManager.core.application.exceptions.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ServerInternalError extends RuntimeException {
   String message;
   String error;

   public ServerInternalError(String message) {
      this.message = message;
   }
   public ServerInternalError(String message, String error) {
      this.message = message;
      this.error = error;
   }
}
