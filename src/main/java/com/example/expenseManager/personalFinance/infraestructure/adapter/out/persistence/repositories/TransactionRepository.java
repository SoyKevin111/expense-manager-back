package com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories;

import com.example.expenseManager.core.application.mappers.EntityGeneralMapper;
import com.example.expenseManager.personalFinance.domain.models.Transaction;
import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import com.example.expenseManager.personalFinance.domain.port.out.repositories.ITransactionRepository;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.entities.CategoryEntity;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.entities.TransactionEntity;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.postgresql.TransactionRepositoryPostgresql;
import com.example.expenseManager.user.infraestructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionRepository implements ITransactionRepository {

   @Autowired
   TransactionRepositoryPostgresql transactionRepository;
   @Autowired
   EntityGeneralMapper entityGeneralMapper;

   @Override
   public Transaction save(Transaction transaction) { //User, Category, ENUM
      TransactionEntity transactionEntity = this.entityGeneralMapper.toEntity(transaction, TransactionEntity.class);
      transactionEntity.setUser(
         this.entityGeneralMapper.toEntity(transaction.getUser(), UserEntity.class)
      );
      transactionEntity.setCategory(
         this.entityGeneralMapper.toEntity(transaction.getCategory(), CategoryEntity.class)
      );
      return this.entityGeneralMapper.toDomain(
         this.transactionRepository.save(transactionEntity), Transaction.class
      );
   }

   @Override
   public void deleteById(Long id) {
      this.transactionRepository.deleteById(id);
   }

   @Override
   public Optional<Transaction> findById(Long id) {
      return this.transactionRepository.findById(id)
         .map(transactionEntity -> this.entityGeneralMapper.toDomain(transactionEntity, Transaction.class));
   }

   @Override
   public List<Transaction> findAll() {
      return this.transactionRepository.findAll()
         .stream()
         .map(transactionEntity -> this.entityGeneralMapper.toDomain(transactionEntity, Transaction.class))
         .toList();
   }

   @Override
   public BigDecimal summaryForTypeAndMonthly(TypeTransaction type, Long userId, int mes) {
      return this.transactionRepository.summaryForTypeAndMonthly(type, userId, mes);
   }

   @Override
   public Page<Transaction> findAllPage(Pageable pageable) {
      return this.transactionRepository.findAll(pageable)
         .map(transactionEntity -> this.entityGeneralMapper.toDomain(transactionEntity, Transaction.class));
   }
}
