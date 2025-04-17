package com.example.expenseManager.personalFinance.domain.port.in.usecases;

import com.example.expenseManager.personalFinance.domain.models.Category;

import java.util.List;

public interface ICategoryUseCase {
   Category create(Category category);
   List<Category> findAll();
}
