package com.example.expenseManager.infraestructure.adapter.out.persistence.repositories;


import com.example.expenseManager.core.mapper.EntityGeneralMapper;
import com.example.expenseManager.domain.models.Category;
import com.example.expenseManager.domain.port.out.ICategoryRepository;
import com.example.expenseManager.infraestructure.adapter.out.persistence.entities.CategoryEntity;
import com.example.expenseManager.infraestructure.adapter.out.persistence.repositories.postgresql.CategoryRepositoryPostgresql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
