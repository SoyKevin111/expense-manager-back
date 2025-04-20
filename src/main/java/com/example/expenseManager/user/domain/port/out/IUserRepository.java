package com.example.expenseManager.user.domain.port.out;

import com.example.expenseManager.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
   User save(User user);
   void deleteById(Long id);
   Optional<User> findById(Long id);
   List<User> findAll();
   boolean existsByEmail(String email);
   boolean existsByIdentification(String identification);
}
