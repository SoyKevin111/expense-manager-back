package com.example.expenseManager.personalFinance.application.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FinancialStatusResponse {
   String income;
   String expense;
   String savingsIn;
   String savingsOut;
}
