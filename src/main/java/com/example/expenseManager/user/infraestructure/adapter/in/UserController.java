package com.example.expenseManager.user.infraestructure.adapter.in;

import com.example.expenseManager.core.application.mappers.RequestGeneralMapper;
import com.example.expenseManager.user.application.mapping.UserUpdaterMapping;
import com.example.expenseManager.user.application.request.CreateUserRequest;
import com.example.expenseManager.user.application.request.UpdateUserRequest;
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

   @PostMapping("/users")
   public ResponseEntity<?> create(@RequestBody @Valid CreateUserRequest createUserRequest) {
      User user = this.requestMapper.toDomain(createUserRequest, User.class);
      User userResponse = this.userUseCase.save(user);
      if (userResponse != null) {
         return ResponseEntity.ok().body(userResponse);
      }
      return ResponseEntity.badRequest().build();
   }

   @PutMapping("/users/{id}") //free name, email
   public ResponseEntity<?> update(@RequestBody @Valid UpdateUserRequest updateUserRequest, @PathVariable Long id) {
      User user = this.userUpdaterMapping.userUpdater(updateUserRequest, id);
      User userResponse = this.userUseCase.save(user); //with id
      if (userResponse != null) {
         return ResponseEntity.ok().body(userResponse);
      }
      return ResponseEntity.badRequest().build();
   }


}
