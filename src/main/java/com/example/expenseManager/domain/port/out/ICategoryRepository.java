package com.example.expenseManager.domain.port.out;

import com.example.expenseManager.domain.models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {

   Category save(Category category);
   List<Category> findAll();
}
