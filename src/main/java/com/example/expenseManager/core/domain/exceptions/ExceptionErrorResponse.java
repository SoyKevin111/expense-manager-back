package com.example.expenseManager.core.domain.exceptions;

import com.example.expenseManager.core.domain.exceptions.dto.FieldErrorDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionErrorResponse {
   private LocalDateTime timestamp;
   private int status;
   private String error;
   private String message;
   private List<FieldErrorDTO> errors;

   public ExceptionErrorResponse(
      LocalDateTime timestamp,
      int status,
      String error
   ) {
      this.timestamp = timestamp;
      this.status = status;
      this.error = error;
   }
}
