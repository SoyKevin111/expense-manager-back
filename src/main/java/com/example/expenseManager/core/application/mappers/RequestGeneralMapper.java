package com.example.expenseManager.core.application.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RequestGeneralMapper {

   private final ModelMapper mapper = new ModelMapper();

   //Request to Domain
   public <D, R> D toDomain(R request, Class<D> domainClass) {
      return mapper.map(request, domainClass);
   }

   public <R, D> R toRequest(D domainObject, Class<R> requestClass) {
      return mapper.map(domainObject, requestClass);
   }

}
