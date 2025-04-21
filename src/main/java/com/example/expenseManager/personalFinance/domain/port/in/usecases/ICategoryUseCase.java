package com.example.expenseManager.personalFinance.domain.port.in.usecases;

import com.example.expenseManager.personalFinance.domain.models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryUseCase {
   Category create(Category category);
   List<Category> findAll();
    Optional<Category> findById(Long id);
}
