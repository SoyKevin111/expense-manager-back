package com.example.expenseManager.auth.application.service;

import com.example.expenseManager.auth.application.dto.AuthLoginUserRequest;
import com.example.expenseManager.auth.application.dto.AuthRegisterUserRequest;
import com.example.expenseManager.auth.application.dto.AuthResponse;
import com.example.expenseManager.auth.infraestructure.util.JwtUtils;
import com.example.expenseManager.core.application.exceptions.models.UnauthorizedAccessException;
import com.example.expenseManager.core.application.mappers.EntityGeneralMapper;
import com.example.expenseManager.user.domain.RoleEnum;
import com.example.expenseManager.user.domain.User;
import com.example.expenseManager.user.domain.port.in.IUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserAuthService implements UserDetailsService {

   @Autowired
   private JwtUtils jwtUtils;
   @Autowired
   private IUserUseCase userUseCase;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @Autowired
   private EntityGeneralMapper mapper;

   //al iniciar sesion
   public AuthResponse loginUser(AuthLoginUserRequest authLoginUserRequest) {
      String email = authLoginUserRequest.getEmail();
      String password = authLoginUserRequest.getPassword();
      Authentication authentication = this.authenticate(email, password);
      SecurityContextHolder.getContext().setAuthentication(authentication); //inica la sesion

      String accessToken = jwtUtils.createToken(authentication);
      return new AuthResponse(
         email,
         "Usuario cargado!",
         accessToken,
         true);
   }

   //obtiene la sesion
   public Authentication authenticate(String email, String password) {
      UserDetails userDetails = this.loadUserByUsername(email);
      if (userDetails == null) {
         throw new BadCredentialsException("Invalido username o password");
      }
      if (!passwordEncoder.matches(password, userDetails.getPassword())) {
         throw new BadCredentialsException("Password Invalido.");
      }
      return new UsernamePasswordAuthenticationToken(
         email,
         userDetails.getPassword(),
         userDetails.getAuthorities());
   }


   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userUseCase.findByEmail(username);
      //Asignar Roles
      List<GrantedAuthority> authorities = List.of(
         new SimpleGrantedAuthority("ROLE_" + user.getRole())
      );
      return new org.springframework.security.core.userdetails.User(
         user.getUsername(),
         user.getPassword(),
         true, true, true, true,
         authorities
      );
   }

   public AuthResponse createUser(AuthRegisterUserRequest authRegisterUserRequest) {
      String email = authRegisterUserRequest.getEmail();
      String password = authRegisterUserRequest.getPassword();

      if (!isAdmin() && authRegisterUserRequest.getRole() != RoleEnum.USER && authRegisterUserRequest.getRole() != null) {
         throw new UnauthorizedAccessException("You don't have permissions to assign this role");
      }

      User user = User.builder()
         .username(authRegisterUserRequest.getUsername())
         .email(email)
         .password(passwordEncoder.encode(password)) // Encriptar contraseña
         .role(authRegisterUserRequest.getRole())
         .build();
      User userSaved = userUseCase.save(user);

      // Asignar rol
      Collection<GrantedAuthority> authorities = List.of(
         new SimpleGrantedAuthority("ROLE_".concat(userSaved.getRole().name()))
      );

      // Crear autenticación y establecer en el contexto
      Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved.getEmail(), null, authorities);
      SecurityContextHolder.getContext().setAuthentication(authentication); //inicia la sesion

      // Generar token
      String accessToken = jwtUtils.createToken(authentication);

      return new AuthResponse(
         userSaved.getEmail(),
         "Usuario creado correctamente.",
         accessToken,
         true
      );
   }

   boolean isAdmin() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
   }

}
