package com.example.expenseManager.domain.models;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
   Long id;
   BigDecimal amount;
   LocalDate createdAt;
   TypeTransaction typeTransaction;
   User user;
   Category category;
}
