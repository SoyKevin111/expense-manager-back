package com.example.expenseManager.personalFinance.domain.models;

import com.example.expenseManager.user.domain.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
   Long id;
   BigDecimal amount;
   LocalDateTime createdDateTime;
   TypeTransaction typeTransaction;
   User user;
   Category category;
}
