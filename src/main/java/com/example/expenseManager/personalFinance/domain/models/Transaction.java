package com.example.expenseManager.personalFinance.domain.models;

import com.example.expenseManager.user.domain.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
