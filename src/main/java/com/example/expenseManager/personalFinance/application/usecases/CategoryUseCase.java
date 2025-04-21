package com.example.expenseManager.personalFinance.application.usecases;

import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.personalFinance.domain.models.Category;
import com.example.expenseManager.personalFinance.domain.port.in.usecases.ICategoryUseCase;
import com.example.expenseManager.personalFinance.domain.port.out.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

   @Override
   public Optional<Category> findById(Long id) {
      try {
         return this.categoryRepository.findById(id);
      } catch (Exception e) {
         throw new ServerInternalError();
      }
   }
}
