package com.example.expenseManager.core.application.dto;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PagesRequest {

   @NotNull(message = "Page number cannot be null")
   int page;

   //opcional
   int size;
   String sortBy;

   public int getSize() {
      if (this.size < 1 || this.size > 5) {
         return 5;
      }
      return this.size;
   }

   public String getSortBy() {
      return this.sortBy == null || this.sortBy.isBlank() ? "id" : this.sortBy;
   }
}

/*
    {
        "page": 0,
        "size": 5,
        "sortBy": "id"
    }
 */