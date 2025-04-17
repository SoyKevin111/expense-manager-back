package com.example.expenseManager.application.usecases;

import com.example.expenseManager.core.exception.models.ServerInternalError;
import com.example.expenseManager.domain.models.Category;
import com.example.expenseManager.domain.port.in.usecases.ICategoryUseCase;
import com.example.expenseManager.domain.port.out.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryUseCase implements ICategoryUseCase {

   @Autowired
   ICategoryRepository categoryRepository;

   @Override
   public Category create(Category category) {
      try {
         return this.categoryRepository.save(category);
      } catch (Exception ex) {
         throw new ServerInternalError();
      }
   }

   @Override
   public List<Category> findAll() {
      try {
         return this.categoryRepository.findAll();
      } catch (Exception e) {
         throw new ServerInternalError();
      }
   }
}
