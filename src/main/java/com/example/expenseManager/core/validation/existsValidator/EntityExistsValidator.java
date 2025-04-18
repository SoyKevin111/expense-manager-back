package com.example.expenseManager.core.validation.existsValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EntityExistsValidator implements ConstraintValidator<EntityExists, Long> {

   private final Map<Class<?>, JpaRepository<?, Long>> repositories;
   private Class<?> entityClass;

   public EntityExistsValidator(Map<Class<?>, JpaRepository<?, Long>> repositories) {
      this.repositories = repositories;
   }

   @Override
   public void initialize(EntityExists constraintAnnotation) {
      this.entityClass = constraintAnnotation.entity();
   }
   @Override
   public boolean isValid(Long value, ConstraintValidatorContext context) {
      JpaRepository<?, Long> repo = repositories.get(entityClass);
      return !repo.existsById(value);
   }


}
