package com.example.expenseManager.core.application.validations;

import com.example.expenseManager.core.application.exceptions.models.ConflictValidationException;
import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IdExistenceValidator {

   private final Map<Class<?>, JpaRepository<?, Long>> repositories;

   @Autowired
   public IdExistenceValidator(Map<Class<?>, JpaRepository<?, Long>> repository) {
      this.repositories = repository;
   }

   public void validateExistsException(Class<?> entityClass, Long id) {
      JpaRepository<?, Long> repo = repositories.get(entityClass);
      if (repo == null) {
         throw new ServerInternalError("Repository not found for entity class: " + entityClass.getSimpleName());
      }
      if (repo.existsById(id)) {
         throw new ConflictValidationException("Id already exists: " + id);
      }
   }

   public boolean validateExists(Class<?> entityClass, Long id) {
      JpaRepository<?, Long> repo = repositories.get(entityClass);
      if (repo == null) {
         throw new ServerInternalError("Repository not found for entity class: " + entityClass.getSimpleName());
      }
      return repo.existsById(id);
   }
}
