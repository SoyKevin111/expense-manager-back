package com.example.expenseManager.personalFinance.application;

import com.example.expenseManager.personalFinance.application.usecases.CategoryUseCase;
import com.example.expenseManager.personalFinance.domain.models.Category;
import com.example.expenseManager.personalFinance.infraestructure.adapter.out.persistence.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryUseCaseTest {
   @Mock
   private CategoryRepository categoryRepository;

   @InjectMocks
   private CategoryUseCase categoryUseCase; //se inyecta el repository

   public Category CATEGORY_PREPARED = Category.builder()
      .id(1L)
      .name("recurso escuela")
      .description("para la escuela")
      .build();

   @Test
   void create() {
      //simula repositorio
      when(categoryRepository.save(CATEGORY_PREPARED)).thenReturn(CATEGORY_PREPARED);
      when(categoryRepository.findAll()).thenReturn(Arrays.asList(CATEGORY_PREPARED, CATEGORY_PREPARED, CATEGORY_PREPARED));

      //crea y obtiene simulando al servico
      categoryUseCase.create(CATEGORY_PREPARED);
      List<Category> categoryList = categoryUseCase.findAll();
      System.out.println(categoryList);
   }



}
