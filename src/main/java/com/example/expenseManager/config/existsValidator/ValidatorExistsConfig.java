package com.example.expenseManager.config.existsValidator;

import com.example.expenseManager.personalFinance.domain.models.Category;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.CategoryRepository;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.postgresql.CategoryRepositoryPostgresql;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ValidatorExistsConfig {

   @Bean
   public Map<Class<?>, JpaRepository<?, Long>> entityRepositories(
      CategoryRepositoryPostgresql categoryRepository
   ) {
      Map<Class<?>, JpaRepository<?, Long>> map = new HashMap<>();
      map.put(Category.class, categoryRepository);
      return map;
   }
}
