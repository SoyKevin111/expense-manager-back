package com.example.expenseManager.user.infraestructure.adapter.in;

import com.example.expenseManager.core.application.mappers.RequestGeneralMapper;
import com.example.expenseManager.user.application.UserUpdaterMapping;
import com.example.expenseManager.user.application.dto.request.CreateUserRequest;
import com.example.expenseManager.user.application.dto.request.UpdateUserRequest;
import com.example.expenseManager.user.application.UserRequestValidation;
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
   private UserUpdaterMapping userUpdaterMapping;
   @Autowired
   private UserRequestValidation userRequestValidation;

   @PostMapping("/users")
   public ResponseEntity<?> create(@RequestBody @Valid CreateUserRequest createUserRequest) {
      this.userRequestValidation.validateCreate(createUserRequest); //validation conflict email, identification
      User user = this.requestMapper.toDomain(createUserRequest, User.class);
      User userResponse = this.userUseCase.save(user);
      return ResponseEntity.ok().body(userResponse);
   }

   @PutMapping("/users/{id}") //free name, email
   public ResponseEntity<?> update(@RequestBody @Valid UpdateUserRequest updateUserRequest, @PathVariable Long id) {
      this.userRequestValidation.validateUpdate(updateUserRequest, id); //validation conflict email
      User user = this.userUpdaterMapping.userUpdater(updateUserRequest, id);
      User userResponse = this.userUseCase.save(user); //with id
      return ResponseEntity.ok().body(userResponse);
   }

   @DeleteMapping("/users/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) {
      this.userUseCase.delete(id);
      return ResponseEntity.ok().build();
   }

   @GetMapping("/users/{id}")
   public ResponseEntity<?> findById(@PathVariable Long id) {
      return ResponseEntity.ok().body(this.userUseCase.findById(id));
   }

   @GetMapping("/users")
   public ResponseEntity<?> findAll() {
      return ResponseEntity.ok().body(this.userUseCase.findAll());
   }


}
