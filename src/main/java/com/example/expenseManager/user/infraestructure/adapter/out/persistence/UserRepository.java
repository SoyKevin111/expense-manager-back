package com.example.expenseManager.user.infraestructure.adapter.out.persistence;

import com.example.expenseManager.core.application.mappers.EntityGeneralMapper;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.out.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

   @Autowired
   private UserRepositoryPostgresql userRepository;
   @Autowired
   private EntityGeneralMapper mapper;

   @Override
   public User save(User User) {
      UserEntity userEntity = mapper.toEntity(User, UserEntity.class);
      return mapper.toDomain(userRepository.save(userEntity), User.class);
   }

   @Override
   public void deleteById(Long id) {
      this.userRepository.deleteById(id);
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
