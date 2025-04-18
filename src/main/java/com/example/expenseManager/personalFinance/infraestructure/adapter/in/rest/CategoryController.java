package com.example.expenseManager.personalFinance.infraestructure.adapter.in.rest;

import com.example.expenseManager.personalFinance.application.request.category.CreateCategoryRequest;
import com.example.expenseManager.core.mapper.RequestGeneralMapper;
import com.example.expenseManager.personalFinance.application.request.category.IdCategoryRequest;
import com.example.expenseManager.personalFinance.domain.models.Category;
import com.example.expenseManager.personalFinance.domain.port.in.usecases.ICategoryUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/request")
public class CategoryController {

   @Autowired
   private ICategoryUseCase categoryUseCase;
   @Autowired
   private RequestGeneralMapper requestMapper;

   @PostMapping("/categories")
   public ResponseEntity<?> create(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
      Category category = this.requestMapper.toDomain(createCategoryRequest, Category.class);
      Category categoryResponse = this.categoryUseCase.create(category);
      if (categoryResponse != null) {
         return ResponseEntity.ok().body(categoryResponse);
      }
      return ResponseEntity.badRequest().build();
   }

   @GetMapping("/categories")
   public List<Category> findAll() {
      return this.categoryUseCase.findAll();
   }

   @PostMapping("/categories/id")
   public String validateId(@RequestBody @Valid IdCategoryRequest idCategoryRequest) {
      return "ID: " + idCategoryRequest.getId() + " is valid.";
   }

}
