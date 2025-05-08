package com.example.expenseManager.personalFinance.application.dto.response;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FinancialStatusResponse {
   BigDecimal income;
   BigDecimal expense;
   BigDecimal savingsIn;
   BigDecimal savingsOut;
}
