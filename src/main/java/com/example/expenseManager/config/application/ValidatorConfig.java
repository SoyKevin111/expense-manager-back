package com.example.expenseManager.config.application;

import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.entities.CategoryEntity;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.entities.TransactionEntity;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.postgresql.CategoryRepositoryPostgresql;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.postgresql.TransactionRepositoryPostgresql;
import com.example.expenseManager.user.infraestructure.adapter.out.persistence.entity.UserEntity;
import com.example.expenseManager.user.infraestructure.adapter.out.persistence.repositories.UserRepositoryPostgresql;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

@Configuration
public class ValidatorConfig {

   @Bean //Mis repositorios disponibles para validacion.
   public Map<Class<?>, JpaRepository<?, Long>> repositoryMap(
      CategoryRepositoryPostgresql categoryRepository,
      UserRepositoryPostgresql userRepository,
      TransactionRepositoryPostgresql transactionRepository
   ) {
      return Map.of(
         CategoryEntity.class, categoryRepository,
         UserEntity.class, userRepository,
         TransactionEntity.class, transactionRepository
      );
   }

}
