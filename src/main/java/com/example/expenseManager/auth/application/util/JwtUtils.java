package com.example.expenseManager.auth.application.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.expenseManager.core.application.exceptions.models.UnauthorizedAccessException;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
public class JwtUtils {
   Dotenv dotenv = Dotenv.load(); //desde el .env

   private String jwtSecretKey;
   private String jwtUser;
   private Instant now = Instant.now();
   private Instant expirationTime = now.plus(30, ChronoUnit.MINUTES); //30 minutos

   public JwtUtils() {
      this.jwtSecretKey = dotenv.get("JWT_SECRET_KEY");
      this.jwtUser = dotenv.get("JWT_USER");
   }

   //Crear un Token
   public String createToken(Authentication authentication) {
      try {
         System.out.println("private Key: " + this.jwtSecretKey);
         Algorithm algorithm = Algorithm.HMAC256(this.jwtSecretKey);
         String email = authentication.getPrincipal().toString();

         String authorities = authentication.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

         return JWT.create() //creando el token
            .withIssuer(this.jwtUser) //autor
            .withSubject(email)
            .withClaim("authorities", authorities)
            .withIssuedAt(Date.from(now))
            .withExpiresAt(Date.from(expirationTime)) //30 minutos
            .withJWTId(UUID.randomUUID().toString())
            .withNotBefore(new Date(System.currentTimeMillis()))
            .sign(algorithm);
      } catch (Exception e) {
         System.err.println("Error al crear el token: " + e.getMessage());
         return null;
      }

   }

   //validacion del token al acceder a algun recurso
   public DecodedJWT validateToken(String token) {

      try {
         System.out.println("validando el token: " + token);
         Algorithm algorithm = Algorithm.HMAC256(this.jwtSecretKey);
         JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(this.jwtUser)
            .build();
         return verifier.verify(token); //DecodeJWT

      } catch (JWTVerificationException e) {
         System.err.println("Error al validar el token: " + e.getMessage());
         throw new UnauthorizedAccessException("Token no valido: " + e.getMessage());
      }


   }

   public String extractEmail(DecodedJWT decodedJWT) {
      return decodedJWT.getSubject(); //return email
   }

   public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
      return decodedJWT.getClaim(claimName);
   }

}
