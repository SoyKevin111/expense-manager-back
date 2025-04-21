package com.example.expenseManager.personalFinance.application.handler;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.core.application.validations.IdExistenceValidator;
import com.example.expenseManager.personalFinance.application.dto.request.MonthlySummaryRequest;
import com.example.expenseManager.personalFinance.application.dto.response.MonthlySummaryResponse;
import com.example.expenseManager.personalFinance.domain.port.out.repositories.ITransactionRepository;
import com.example.expenseManager.user.infraestructure.adapter.out.persistence.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GetMonthlySummaryHandler {

   @Autowired
   ITransactionRepository transactionRepository;
   @Autowired
   IdExistenceValidator idExistenceValidator;

   public MonthlySummaryResponse typeHandle(MonthlySummaryRequest monthlySummaryRequest) { //por tipo
      if (!this.idExistenceValidator.validateExists(UserEntity.class, monthlySummaryRequest.getUserId())) {
         throw new ServerInternalError("User not found");
      }

      try {
         BigDecimal amount = this.transactionRepository.summaryForTypeAndMonthly(
            monthlySummaryRequest.getType(),
            monthlySummaryRequest.getUserId(),
            monthlySummaryRequest.getMonth()
         );
         return new MonthlySummaryResponse(
            monthlySummaryRequest.getType(),
            amount != null ? amount : BigDecimal.ZERO
         );
      } catch (Exception e) {
         throw new ServerInternalError("Error finding summary for type and monthly");
      }


   }
   //despues haremos, que retorne un resumen de todos los tipos de transacciones, y no solo por tipo
}
