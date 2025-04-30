package com.example.expenseManager.user.infraestructure.adapter.in;

import com.example.expenseManager.core.application.mappers.RequestGeneralMapper;
import com.example.expenseManager.user.application.UpdateProfileMapping;
import com.example.expenseManager.user.application.UpdateUserMapping;
import com.example.expenseManager.user.application.dto.request.CreateUserRequest;
import com.example.expenseManager.user.application.dto.request.UpdateProfileRequest;
import com.example.expenseManager.user.application.dto.request.UpdateUserRequest;
import com.example.expenseManager.user.application.validation.CreateUserValidator;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.in.IUserUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/request")
public class UserController {

   @Autowired
   IUserUseCase userUseCase;
   @Autowired
   private RequestGeneralMapper requestMapper;
   @Autowired
   private UpdateUserMapping updateUserMapping;
   @Autowired
   private UpdateProfileMapping updateProfileMapping;
   @Autowired
   private CreateUserValidator createUserValidator;

   @PostMapping("/users")
   public ResponseEntity<?> create(@RequestBody CreateUserRequest createUserRequest) {
      User user = this.requestMapper.toDomain(createUserValidator.validate(createUserRequest), User.class); //valida y mapea datos.
      User userResponse = this.userUseCase.save(user);
      return ResponseEntity.ok().body(userResponse);
   }

   @PutMapping("/users/{id}") //rol: admin
   public ResponseEntity<?> update(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable Long id) {
      User user = this.updateUserMapping.toDomainModel(updateUserRequest, id);
      User userResponse = this.userUseCase.save(user); //with id
      return ResponseEntity.ok().body(userResponse);
   }

   @DeleteMapping("/users/{id}") //rol: admin
   public ResponseEntity<?> delete(@PathVariable Long id) {
      this.userUseCase.delete(id);
      return ResponseEntity.ok().build();
   }

   @GetMapping("/users/{id}") //rol: admin
   public ResponseEntity<?> findById(@PathVariable Long id) {
      return ResponseEntity.ok().body(this.userUseCase.findById(id));
   }

   @GetMapping("/users") //rol: admin
   public ResponseEntity<?> findAll() {
      return ResponseEntity.ok().body(this.userUseCase.findAll());
   }

   @PatchMapping("/users/profile/{id}") //rol: user, admin
   public ResponseEntity<?> update(@RequestBody @Valid UpdateProfileRequest updateProfileRequest, @PathVariable Long id) {
      User userResponse = this.updateProfileMapping.toDomainModel(updateProfileRequest, id);
      return ResponseEntity.ok().body(this.userUseCase.updateProfile(userResponse));
   }
}
