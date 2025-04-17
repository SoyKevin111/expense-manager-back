package com.example.expenseManager.infraestructure.adapter.out.persistence.entities;

import com.example.expenseManager.domain.models.TypeTransaction;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;

   //@NotNull(message = "Amount cannot be null")
   BigDecimal amount;

   @Column(name = "created_at")
   //@NotNull(message = "createdAt cannot be null")
   LocalDate createdAt;

   @Enumerated(EnumType.STRING)
   @Column(name = "type_transaction")
   //@NotNull(message = "TypeTransaction cannot be null")
   TypeTransaction typeTransaction;

   @ManyToOne
   @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
   UserEntity user;

   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
   CategoryEntity category;
}
