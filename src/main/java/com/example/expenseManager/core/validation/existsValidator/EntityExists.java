package com.example.expenseManager.core.validation.existsValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EntityExistsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityExists {
   String message() default "Entity does not exist";
   Class<?> entity();
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};

}
