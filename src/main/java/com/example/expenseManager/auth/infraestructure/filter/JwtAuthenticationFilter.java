package com.example.expenseManager.auth.infraestructure.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.expenseManager.auth.application.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

   JwtUtils jwtUtils;

   public JwtAuthenticationFilter(JwtUtils jwtUtils) {
      this.jwtUtils = jwtUtils;
   }

   @Override
   protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
   ) throws ServletException, IOException {

      String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
         String jwtToken = authorizationHeader.substring(7);

         DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

         String email = jwtUtils.extractEmail(decodedJWT);
         String authoritiesClaim = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

         Collection<? extends GrantedAuthority> authorities =
            AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim);

         SecurityContext context = SecurityContextHolder.createEmptyContext();
         Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);

         context.setAuthentication(authentication);
         SecurityContextHolder.setContext(context);


      }
      filterChain.doFilter(request, response);
   }


}
