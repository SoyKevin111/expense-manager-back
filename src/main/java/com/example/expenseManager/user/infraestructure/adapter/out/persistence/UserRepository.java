package com.example.expenseManager.user.infraestructure.adapter.out.persistence;

import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.out.IUserRepository;
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
