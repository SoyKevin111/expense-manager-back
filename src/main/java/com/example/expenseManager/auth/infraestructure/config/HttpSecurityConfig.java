package com.example.expenseManager.auth.infraestructure.config;

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

   @Bean
   @Order(1)
   public SecurityFilterChain authSecurity(HttpSecurity httpSecurity) throws Exception {
      return httpSecurity
         .securityMatcher("/manager/auth/**") // endpoints que comiencen con manager/auth/
         .csrf(csrf -> csrf.disable())
         .httpBasic(Customizer.withDefaults())
         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .authorizeHttpRequests(http -> {
            //Publicos
            http.requestMatchers(HttpMethod.POST, "/manager/auth/sign-up", "/manager/auth/log-in").permitAll();
            http.anyRequest().authenticated();//denegamos el acceso a cualquier otro endpoint
         })
         .addFilterBefore(new JwtAuthenticationFilter(jwtUtils), BasicAuthenticationFilter.class)
         .build();
   }

   @Bean
   @Order(2)
   public SecurityFilterChain userSecurity(HttpSecurity httpSecurity) throws Exception {
      return httpSecurity
         .securityMatcher("/manager/request/users/**") // endpoints que comiencen con manager/request/users/
         .csrf(csrf -> csrf.disable())
         .httpBasic(Customizer.withDefaults())
         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .authorizeHttpRequests(http -> {
            //Publicos
            http.requestMatchers(HttpMethod.GET, "/manager/request/users/*").permitAll();
            //Privados
            http.requestMatchers(HttpMethod.GET, "/manager/request/users").hasRole("USER");
            http.requestMatchers(HttpMethod.POST, "/manager/request/users").hasRole("ADMIN");
            http.requestMatchers(HttpMethod.PUT, "/manager/request/users").hasRole("ADMIN");
            http.requestMatchers(HttpMethod.DELETE, "/manager/request/users").hasRole("ADMIN");
            http.anyRequest().authenticated();//denegamos el acceso a cualquier otro endpoint
         })
         .addFilterBefore(new JwtAuthenticationFilter(jwtUtils), BasicAuthenticationFilter.class)
         .build();
   }

}
