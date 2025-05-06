package com.example.expenseManager.personalFinance.application.handler;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.core.application.validations.IdExistenceValidator;
import com.example.expenseManager.personalFinance.application.dto.request.MonthlySummaryRequest;
import com.example.expenseManager.personalFinance.application.dto.response.MonthlySummaryResponse;
import com.example.expenseManager.personalFinance.domain.port.out.repositories.ITransactionRepository;
import com.example.expenseManager.user.infraestructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GetMonthlySummaryHandler {

   @Autowired
   ITransactionRepository transactionRepository;
   @Autowired
   IdExistenceValidator idExistenceValidator;

}
