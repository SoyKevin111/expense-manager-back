package com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories;


import com.example.expenseManager.core.application.mappers.EntityGeneralMapper;
import com.example.expenseManager.personalFinance.domain.models.Category;
import com.example.expenseManager.personalFinance.domain.port.out.repositories.ICategoryRepository;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.entities.CategoryEntity;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.postgresql.CategoryRepositoryPostgresql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository implements ICategoryRepository {

   @Autowired
   CategoryRepositoryPostgresql categoryRepository;
   @Autowired
   EntityGeneralMapper generalMapper;

   @Override
   public Category save(Category category) {
      CategoryEntity categoryEntity = this.generalMapper.toEntity(category, CategoryEntity.class);
      return this.generalMapper.toDomain(this.categoryRepository.save(categoryEntity), Category.class);
   }

   @Override
   public List<Category> findAll() {
      return ((List<CategoryEntity>) this.categoryRepository.findAll())
         .stream()
         .map(entity -> this.generalMapper.toDomain(entity, Category.class))
         .toList();
   }

   @Override
   public Optional<Category> findById(Long id) {
      return this.categoryRepository.findById(id)
         .map(entity -> this.generalMapper.toDomain(entity, Category.class));
   }
}
