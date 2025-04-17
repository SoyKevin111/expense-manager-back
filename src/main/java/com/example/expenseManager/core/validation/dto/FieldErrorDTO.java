package com.example.expenseManager.core.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FieldErrorDTO {
   private String field;
   private String message;
}
