package com.example.expenseManager.infraestructure.adapter.in.rest;

import com.example.expenseManager.application.request.CategoryRequest;
import com.example.expenseManager.core.mapper.RequestGeneralMapper;
import com.example.expenseManager.domain.models.Category;
import com.example.expenseManager.domain.port.in.usecases.ICategoryUseCase;
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
   //mapper
   @Autowired
   private RequestGeneralMapper requestMapper;

   @PostMapping("/categories")
   public ResponseEntity<?> create(@RequestBody @Valid CategoryRequest categoryRequest) {
      Category category = this.requestMapper.toDomain(categoryRequest, Category.class);
      Category categoryResponse = this.categoryUseCase.create(category);
      if (categoryResponse != null) {
         return ResponseEntity.ok().body(categoryResponse);
      }
      return ResponseEntity.badRequest().build();
   }

   @GetMapping("/categories")
   public List<Category> findAll(){
      return this.categoryUseCase.findAll();
   }


}
