package com.example.expenseManager;

import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.entities.CategoryEntity;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.CategoryRepository;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.postgresql.CategoryRepositoryPostgresql;
import com.example.expenseManager.user.infraestructure.adapter.out.persistence.UserEntity;
import com.example.expenseManager.user.infraestructure.adapter.out.persistence.UserRepositoryPostgresql;
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
   public CommandLineRunner demo(
      CategoryRepositoryPostgresql categoryRepository,
      UserRepositoryPostgresql userRepository
   ) {
      return args -> {

         CategoryEntity categoryEntity1 = CategoryEntity.builder()
            .name("Hogar")
            .description("Ingresos o gastos del hogar.")
            .build();

         CategoryEntity categoryEntity2 = CategoryEntity.builder()
            .name("Escuela")
            .description("Ingresos o gastos para la escuela.")
            .build();

         UserEntity userEntity1 = UserEntity.builder()
            .name("kevin steven raton")
            .email("kevin@gmail.com")
            .identification("0953499587")
            .build();

         UserEntity userEntity2 = UserEntity.builder()
            .name("kevin steven raton")
            .email("kevin3@gmail.com")
            .identification("2953499587")
            .build();

         categoryRepository.saveAll(List.of(categoryEntity1, categoryEntity2));
         userRepository.saveAll((List.of(userEntity1, userEntity2)));
      };
   }

}
