package com.example.expenseManager;

import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.entities.CategoryEntity;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.CategoryRepository;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.postgresql.CategoryRepositoryPostgresql;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ExpenseManagerApplication {

   public static void main(String[] args) {
      SpringApplication.run(ExpenseManagerApplication.class, args);
   }

   @Bean
   public CommandLineRunner demo(CategoryRepositoryPostgresql categoryRepository) {
      return args -> {

         CategoryEntity categoryEntity1 = CategoryEntity.builder()
            .name("Hogar")
            .description("Ingresos o gastos del hogar.")
            .build();

         CategoryEntity categoryEntity2 = CategoryEntity.builder()
            .name("Escuela")
            .description("Ingresos o gastos para la escuela.")
            .build();

         categoryRepository.saveAll(List.of(categoryEntity1, categoryEntity2));

      };
   }

}
