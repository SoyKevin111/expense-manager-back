package com.example.expenseManager.core.application.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityGeneralMapper {
   private final ModelMapper mapper = new ModelMapper();

   //entity to domain
   public <D, E> D toDomain(E entity, Class<D> domainClass) {
      return mapper.map(entity, domainClass);
   }

   //domain to entity
   public <E, D> E toEntity(D domainObject, Class<E> entityClass) {
      return mapper.map(domainObject, entityClass);
   }

   public <I, O> I toRequest(O domainObject, Class<I> entityClass) {
      return mapper.map(domainObject, entityClass);
   }

}
