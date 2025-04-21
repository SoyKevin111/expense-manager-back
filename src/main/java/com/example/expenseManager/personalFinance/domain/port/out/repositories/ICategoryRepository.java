package com.example.expenseManager.personalFinance.domain.port.out.repositories;

import com.example.expenseManager.personalFinance.domain.models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {

   Category save(Category category);
   List<Category> findAll();
    Optional<Category> findById(Long id);
}
