package com.example.expenseManager.infraestructure.adapter.out.persistence.repositories;

import com.example.expenseManager.domain.models.User;
import com.example.expenseManager.domain.port.out.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

   @Override
   public User save(User User) {
      return null;
   }

   @Override
   public int deleteById(Long id) {
      return 0;
   }

   @Override
   public Optional<User> findById(Long id) {
      return Optional.empty();
   }

   @Override
   public List<User> findAll() {
      return List.of();
   }
}
