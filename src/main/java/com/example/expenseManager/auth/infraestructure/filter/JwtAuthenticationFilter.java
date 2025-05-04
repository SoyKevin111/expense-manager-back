package com.example.expenseManager.auth.infraestructure.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.expenseManager.auth.infraestructure.util.JwtUtils;
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
import java.time.LocalDateTime;
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

         try {
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            String email = jwtUtils.extractEmail(decodedJWT);
            String authoritiesClaim = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

            Collection<? extends GrantedAuthority> authorities =
               AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);

            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
         } catch (JWTVerificationException ex) {
            JWTVerificationException(ex.getMessage(), response);
            return;
         }

      }
      filterChain.doFilter(request, response);
   }

   public void JWTVerificationException(String message, HttpServletResponse response) throws IOException {
      String json = "{"
         + "\"timestamp\": \"" + LocalDateTime.now() + "\","
         + "\"status\": " + HttpServletResponse.SC_UNAUTHORIZED + ","
         + "\"error\": \"[Unauthorized Error]\","
         + "\"message\": \"" + message + "\""
         + "}";

      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write(json);
   }


}
