package com.example.expenseManager.user.domain.port.in;

import com.example.expenseManager.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserUseCase {
   User save(User user);
   void delete(Long id);
   Optional<User> findById(Long id);
   List<User> findAll();
   User updateProfile(User user);
   User findByEmail(String email);
}
