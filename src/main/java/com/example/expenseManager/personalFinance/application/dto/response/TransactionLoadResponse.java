package com.example.expenseManager.personalFinance.application.dto.response;

import com.example.expenseManager.personalFinance.domain.models.Category;
import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionLoadResponse {
   Long id;
   BigDecimal amount;
   LocalDate createdDate;
   TypeTransaction typeTransaction;
   Long userId;
   Category category;
}
