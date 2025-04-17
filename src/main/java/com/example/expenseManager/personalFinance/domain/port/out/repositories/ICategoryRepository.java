package com.example.expenseManager.personalFinance.domain.port.out.repositories;

import com.example.expenseManager.personalFinance.domain.models.Category;

import java.util.List;

public interface ICategoryRepository {

   Category save(Category category);
   List<Category> findAll();
}
