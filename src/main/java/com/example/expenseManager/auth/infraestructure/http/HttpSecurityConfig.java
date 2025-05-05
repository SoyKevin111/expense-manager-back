package com.example.expenseManager.auth.infraestructure.http;

import com.example.expenseManager.auth.application.service.UserAuthService;
import com.example.expenseManager.auth.infraestructure.util.JwtUtils;
import com.example.expenseManager.auth.infraestructure.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class HttpSecurityConfig {

   @Autowired
   AuthenticationConfiguration authenticationConfiguration; //
   @Autowired
   JwtUtils jwtUtils;

   @Bean
   public AuthenticationProvider authenticationProvider(UserAuthService userAuthService) {
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setPasswordEncoder(passwordEncoder()); //metodo de codificacion
      provider.setUserDetailsService(userAuthService);
      return provider;
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   public AuthenticationManager authenticationManager() throws Exception { // AuthenticationConfiguration se inicializa automaticamente en el parametro
      return this.authenticationConfiguration.getAuthenticationManager();
   }

   private HttpSecurity applyCommonConfig(HttpSecurity http) throws Exception {
      return http
         .csrf(AbstractHttpConfigurer::disable)
         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .addFilterBefore(new JwtAuthenticationFilter(jwtUtils), BasicAuthenticationFilter.class);
   }

   @Bean
   @Order(1)
   public SecurityFilterChain authSecurity(HttpSecurity http) throws Exception {
      applyCommonConfig(http);
      return http
         .securityMatcher("/manager/auth/**") // endpoints que comiencen con manager/auth/
         .authorizeHttpRequests(auth -> {
            //Publicos
            auth.requestMatchers(HttpMethod.POST, "/manager/auth/sign-up", "/manager/auth/log-in").permitAll();
            auth.anyRequest().denyAll();//denegamos el acceso a cualquier otro endpoint
         })
         .addFilterBefore(new JwtAuthenticationFilter(jwtUtils), BasicAuthenticationFilter.class)
         .build();
   }

   @Bean
   @Order(2)
   public SecurityFilterChain usersSecurity(HttpSecurity http) throws Exception {
      applyCommonConfig(http);
      return http
         .securityMatcher("/manager/request/users/**") // endpoints que comiencen con manager/request/users/
         .authorizeHttpRequests(auth -> {
            //USER
            auth.requestMatchers(HttpMethod.GET, "/manager/request/users/*").hasAnyRole("USER", "ADMIN"); //get user by id
            auth.requestMatchers(HttpMethod.PUT, "/manager/request/users/profile/*").hasAnyRole("USER", "ADMIN"); //actualizar perfil
            //ADMIN
            auth.requestMatchers(HttpMethod.GET, "/manager/request/users").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.POST, "/manager/request/users").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.PUT, "/manager/request/users").hasRole("ADMIN");
            auth.requestMatchers(HttpMethod.DELETE, "/manager/request/users").hasRole("ADMIN");
            auth.anyRequest().denyAll();//denegamos el acceso a cualquier otro endpoint
         })
         .addFilterBefore(new JwtAuthenticationFilter(jwtUtils), BasicAuthenticationFilter.class)
         .build();
   }

   @Bean
   @Order(3)
   public SecurityFilterChain transactionsSecurity(HttpSecurity http) throws Exception {
      applyCommonConfig(http);
      return http
         .securityMatcher("/manager/request/transactions/**")
         .authorizeHttpRequests(auth ->
            auth.requestMatchers("/transactions/**").authenticated().anyRequest().permitAll()
         )
         .addFilterBefore(new JwtAuthenticationFilter(jwtUtils), BasicAuthenticationFilter.class)
         .build();
   }

}
