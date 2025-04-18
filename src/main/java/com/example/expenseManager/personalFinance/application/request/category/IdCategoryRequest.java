package com.example.expenseManager.personalFinance.application.request.category;

import com.example.expenseManager.core.validation.existsValidator.EntityExists;
import com.example.expenseManager.personalFinance.domain.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdCategoryRequest {

   @EntityExists(entity = Category.class, message = "Este ID, ya esta registrado.")
   private Long id;
}
