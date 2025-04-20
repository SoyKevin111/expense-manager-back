package com.example.expenseManager.core.application.exceptions;

import com.example.expenseManager.core.application.exceptions.models.ConflictValidationException;
import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.core.domain.exceptions.ExceptionErrorResponse;
import com.example.expenseManager.core.domain.exceptions.dto.FieldErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

   //Validaciones de propiedades con @Valid
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ExceptionErrorResponse> handleProperty(
      MethodArgumentNotValidException ex
   ) {
      List<FieldErrorDTO> errors = ex.getBindingResult().getFieldErrors()
         .stream()
         .map(err -> new FieldErrorDTO(err.getField(), err.getDefaultMessage()))
         .toList();

      ExceptionErrorResponse response = ExceptionErrorResponse.builder()
         .timestamp(LocalDateTime.now())
         .status(HttpStatus.BAD_REQUEST.value())
         .error("[Request Error]")
         .errors(errors)
         .build();

      return ResponseEntity
         .status(HttpStatus.BAD_REQUEST)
         .body(response);
   }

   //Validaciones personalizadas de conflictos en la base de datos
   @ExceptionHandler(ConflictValidationException.class)
   public ResponseEntity<Map<String, Object>> handleConflict(
      ConflictValidationException ex
   ) {
      Map<String, Object> response = Map.of(
         "timestamp", LocalDateTime.now(),
         "status", HttpStatus.CONFLICT.value(),
         "error", "[Conflict Error]",
         "message", ex.getMessage()
      );
      return ResponseEntity
         .status(HttpStatus.CONFLICT)
         .body(response);
   }

   //Validaciones Genericas
   @ExceptionHandler(ServerInternalError.class)
   public ResponseEntity<Map<String, Object>> handleGeneric(ServerInternalError ex) {
      Map<String, Object> response = Map.of(
         "timestamp", LocalDateTime.now(),
         "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
         "error", "[Server Internal Error]",
         "message", ex.getMessage().isEmpty() ? "Server Internal Error" : ex.getMessage()
      );
      return ResponseEntity
         .status(HttpStatus.INTERNAL_SERVER_ERROR)
         .body(response);
   }
}


/*
{
  "timestamp": "2025-04-11T11:00:00",
  "status": 400,
  "error": "[Request Error]",
  "errors": [
    {
      "field": "name",
      "message": "Name is required"
    },
    {
      "field": "surname",
      "message": "Surname is required"
    }
  ],
  ajjdajda cualquier tema de intellji idea XDDDDD
}
 */
