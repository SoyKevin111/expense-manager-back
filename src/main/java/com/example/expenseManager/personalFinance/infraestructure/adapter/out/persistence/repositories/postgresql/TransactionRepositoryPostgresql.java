package com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.postgresql;

import com.example.expenseManager.personalFinance.domain.models.TypeTransaction;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.entities.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepositoryPostgresql extends JpaRepository<TransactionEntity, Long> {

   Page<TransactionEntity> findAll(Pageable pageable);

   @Query("SELECT SUM(t.amount) FROM TransactionEntity t " +
      "WHERE t.typeTransaction = :typeTransaction " +
      "AND t.user.id = :userId " +
      "AND EXTRACT(MONTH FROM t.createdDateTime) = :mes")
   BigDecimal summaryForTypeAndMonthly(@Param("typeTransaction") TypeTransaction typeTransaction, @Param("userId") Long userId, @Param("mes") int mes);

}



/*

@Query("SELECT SUM(t.amount) FROM TransactionEntity t " +
      "WHERE t.typeTransaction = :typeTransaction " +
      "AND t.user.id = :userId " +
      "AND EXTRACT(MONTH FROM t.createdDateTime) = :mes")
BigDecimal summaryForTypeAndMonthly(@Param("typeTransaction") TypeTransaction typeTransaction,
                                     @Param("userId") Long userId,
                                     @Param("mes") int mes);


 */