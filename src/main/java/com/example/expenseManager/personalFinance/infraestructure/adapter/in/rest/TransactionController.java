package com.example.expenseManager.personalFinance.infraestructure.adapter.in.rest;

import com.example.expenseManager.personalFinance.application.dto.request.CreateTransactionRequest;
import com.example.expenseManager.personalFinance.application.dto.request.MonthlySummaryRequest;
import com.example.expenseManager.personalFinance.application.handler.GetMonthlySummaryHandler;
import com.example.expenseManager.personalFinance.application.mapping.CreateTransactionMapping;
import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.port.in.usecases.ITransactionUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/request")
public class TransactionController {

   @Autowired
   ITransactionUseCase transactionUseCase;
   @Autowired
   CreateTransactionMapping createTransactionMapping;
   @Autowired
   GetMonthlySummaryHandler monthlySummaryHandler;

   @PostMapping("/transactions")
   public ResponseEntity<?> createTransaction(@RequestBody @Valid CreateTransactionRequest transactionRequest) {
      Transaction transaction = this.createTransactionMapping.toDomainModel(transactionRequest);
      transaction = this.transactionUseCase.create(transaction);
      return ResponseEntity.ok(transaction);
   }

   @GetMapping("/transactions/monthly")
   public ResponseEntity<?> summaryForTypeAndMonthly(@RequestBody @Valid MonthlySummaryRequest monthlySummaryRequest) {
      return ResponseEntity.ok(monthlySummaryHandler.typeHandle(monthlySummaryRequest));
   }

   @GetMapping("/transactions")
   public List<Transaction> findAll() {
      return this.transactionUseCase.findAll();
   }


}
