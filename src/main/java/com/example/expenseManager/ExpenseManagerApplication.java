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



}
