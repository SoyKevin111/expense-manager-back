package com.example.expenseManager.user.infraestructure.adapter.out.persistence.repositories;

import com.example.expenseManager.core.application.mappers.EntityGeneralMapper;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.out.IUserRepository;
import com.example.expenseManager.user.infraestructure.adapter.out.persistence.entity.UserEntity;
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
   public User save(User user) {
      UserEntity userEntity = mapper.toEntity(user, UserEntity.class);
      return mapper.toDomain(userRepository.save(userEntity), User.class);
   }

   @Override
   public void deleteById(Long id) {
      this.userRepository.deleteById(id);
   }

   @Override
   public Optional<User> findById(Long id) {
      return this.userRepository.findById(id).map(userEntity -> this.mapper.toDomain(userEntity, User.class));
   }

   @Override
   public List<User> findAll() {
      return ((List<UserEntity>) this.userRepository.findAll())
         .stream()
         .map(userEntity -> this.mapper.toDomain(userEntity, User.class))
         .toList();
   }

   @Override
   public boolean existsByEmail(String email) {
      return this.userRepository.existsByEmail(email);
   }

   @Override
   public Optional<User> findByEmail(String email) {
      return this.userRepository.findByEmail(email).map(
         userEntity -> this.mapper.toDomain(userEntity, User.class)
      );
   }

}
