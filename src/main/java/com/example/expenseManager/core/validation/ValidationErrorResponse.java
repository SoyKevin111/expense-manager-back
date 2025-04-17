package com.example.expenseManager.core.validation;

import com.example.expenseManager.core.validation.dto.FieldErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ValidationErrorResponse {
   private LocalDateTime timestamp;
   private int status;
   private String error;
   private List<FieldErrorDTO> errors;
   private String path;
}
