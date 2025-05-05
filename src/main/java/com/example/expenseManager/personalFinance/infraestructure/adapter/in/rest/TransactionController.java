package com.example.expenseManager.personalFinance.infraestructure.adapter.in.rest;

import com.example.expenseManager.personalFinance.application.dto.request.CreateTransactionRequest;
import com.example.expenseManager.personalFinance.application.dto.request.MonthlySummaryRequest;
import com.example.expenseManager.personalFinance.application.dto.request.TransactionPage;
import com.example.expenseManager.personalFinance.application.dto.response.TransactionLoadResponse;
import com.example.expenseManager.personalFinance.application.handler.GetMonthlySummaryHandler;
import com.example.expenseManager.personalFinance.application.mapping.CreateTransactionMapping;
import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import com.example.expenseManager.personalFinance.domain.port.in.usecases.ITransactionUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


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
      Transaction transactionResponse = this.createTransactionMapping.toDomainModel(transactionRequest);
      Transaction transaction = this.transactionUseCase.create(transactionResponse);
      return ResponseEntity.ok(
         TransactionLoadResponse.builder()
            .id(transaction.getId())
            .amount(transaction.getAmount())
            .time(String.valueOf(transaction.getCreatedDateTime().toLocalTime()))
            .date(String.valueOf(transaction.getCreatedDateTime().toLocalDate()))
            .typeTransaction(transaction.getTypeTransaction())
            .userId(transaction.getUser().getId())
            .category(transaction.getCategory())
            .build()
      );
   }

   @GetMapping("transactions/balance-and-savings")
   public ResponseEntity<?> findBalanceAndSavings(Authentication authentication) {
      Map<String, BigDecimal> responseSavingsBalance = Map.of(
         "balance", this.transactionUseCase.findCurrentBalance(authentication.getName()),
         "savings", this.transactionUseCase.findCurrentSavings(authentication.getName())
      );
      return ResponseEntity.ok(responseSavingsBalance);
   }


   @PostMapping("/transactions/monthly")
   public ResponseEntity<?> summaryForTypeAndMonthly(@RequestBody @Valid MonthlySummaryRequest monthlySummaryRequest) {
      return ResponseEntity.ok(monthlySummaryHandler.typeHandle(monthlySummaryRequest));
   }

   //http://localhost:8080/manager/request/transactions/page
   @GetMapping("transactions/page")
   public ResponseEntity<?> findAllPage(@RequestBody @Valid TransactionPage transactionPage) {
      Sort sort = Sort.by(Sort.Order.by(transactionPage.getSortBy()).with(Sort.Direction.fromString(transactionPage.getSortDirection())));
      Page<TransactionLoadResponse> transactionPageable = this.transactionUseCase.findAllPage(PageRequest.of(transactionPage.getPage(), transactionPage.getSize(), sort))
         .map(
            transaction -> TransactionLoadResponse.builder()
               .typeTransaction(transaction.getTypeTransaction())
               .amount(transaction.getAmount())
               .time(String.valueOf(transaction.getCreatedDateTime().toLocalTime()))
               .date(String.valueOf(transaction.getCreatedDateTime().toLocalDate()))
               .userId(transaction.getUser().getId())
               .category(transaction.getCategory())
               .id(transaction.getId())
               .build()
         );
      return ResponseEntity.ok(transactionPageable);
   }
}


/*

A hacer:
 ya tenemos
    Obtener monto total por tipo y mes
    Obtener todos las transacciones en paginacion (Historial)
    Crear cualquier tipo de transaccion

 por hacer:
   obtener todas las transacciones por tipo
   BigDecimal findAllIncomes()
   BigDecimal findAllExpenses()
   BigDecimal findAllSavingsIn()
   BigDecimal findAllSavingsOut()

   BigDecimal findAmounthBytype(TypeTransaction typeTransaction); //INCOME


    Logica de entrada, salida, ahorro, salidaAhorro:
    Ingreso , es el total
    Gasto, se resta del total
    Entrada de Ahorro, se resta del total se suma al ahorro
    Salida de ahorro, se resta del ahorro

 */