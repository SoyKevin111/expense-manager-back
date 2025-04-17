package com.example.expenseManager.domain.port.in.usecases;

import com.example.expenseManager.domain.models.Category;

import java.util.List;

public interface ICategoryUseCase {
   Category create(Category category);
   List<Category> findAll();
}
