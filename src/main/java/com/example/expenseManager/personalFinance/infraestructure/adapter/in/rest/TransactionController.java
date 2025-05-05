package com.example.expenseManager.personalFinance.infraestructure.adapter.in.rest;

import com.example.expenseManager.personalFinance.application.dto.request.CreateTransactionRequest;
import com.example.expenseManager.personalFinance.application.dto.request.MonthlySummaryRequest;
import com.example.expenseManager.personalFinance.application.dto.response.TransactionLoadResponse;
import com.example.expenseManager.personalFinance.application.handler.GetMonthlySummaryHandler;
import com.example.expenseManager.personalFinance.application.mapping.CreateTransactionMapping;
import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.port.in.usecases.ITransactionUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
            .createdDate(transaction.getCreatedDate())
            .typeTransaction(transaction.getTypeTransaction())
            .userId(transaction.getUser().getId())
            .category(transaction.getCategory())
            .build()
      );
   }

   @GetMapping("/transactions/monthly")
   public ResponseEntity<?> summaryForTypeAndMonthly(@RequestBody @Valid MonthlySummaryRequest monthlySummaryRequest) {
      return ResponseEntity.ok(monthlySummaryHandler.typeHandle(monthlySummaryRequest));
   }

   //http://localhost:8080/manager/request/transactions/page?page=0&size=5&sortBy=createdDate
   @GetMapping("transactions/page")
   public ResponseEntity<?> findAllPage(
      @Min(0) @RequestParam(defaultValue = "0") int page,
      @Min(1) @RequestParam(defaultValue = "5") int size,
      @RequestParam(defaultValue = "id") String sortBy
   ) {
      Page<TransactionLoadResponse> transactionPageable = this.transactionUseCase.findAllPage(PageRequest.of(page, size, Sort.by(sortBy))).map(
         transaction -> TransactionLoadResponse.builder()
            .typeTransaction(transaction.getTypeTransaction())
            .amount(transaction.getAmount())
            .createdDate(transaction.getCreatedDate())
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
   findAllIncomes()
   findAllExpenses()
   findAllSavingsIn()
   findAllSavingsOut()



    Logica de entrada, salida, ahorro, salidaAhorro:
    Ingreso , es el total
    Gasto, se resta del total
    Entrada de Ahorro, se resta del total se suma al ahorro
    Salida de ahorro, se resta del ahorro

 */