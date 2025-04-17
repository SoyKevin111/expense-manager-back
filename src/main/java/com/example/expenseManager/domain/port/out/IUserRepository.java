package com.example.expenseManager.domain.port.out;

import com.example.expenseManager.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
   User save(User User);
   int deleteById(Long id);
   Optional<User> findById(Long id);
   List<User> findAll();
}
