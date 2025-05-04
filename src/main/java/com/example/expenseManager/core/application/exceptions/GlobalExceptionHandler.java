package com.example.expenseManager.core.application.exceptions;

import com.example.expenseManager.core.application.exceptions.models.ConflictValidationException;
import com.example.expenseManager.core.application.exceptions.models.ServerInternalError;
import com.example.expenseManager.core.application.exceptions.models.UnauthorizedAccessException;
import com.example.expenseManager.core.domain.exceptions.ExceptionErrorResponse;
import com.example.expenseManager.core.domain.exceptions.dto.FieldErrorDTO;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

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
   public ResponseEntity<ExceptionErrorResponse> handleConflict(
      ConflictValidationException ex
   ) {
      ExceptionErrorResponse response = ExceptionErrorResponse.builder()
         .timestamp(LocalDateTime.now())
         .status(HttpStatus.CONFLICT.value())
         .error("[Conflict Error]")
         .message(ex.getMessage())
         .build();

      return ResponseEntity
         .status(HttpStatus.CONFLICT)
         .body(response);
   }

   //Validaciones de acceso no autorizado
   @ExceptionHandler(UnauthorizedAccessException.class)
   public ResponseEntity<ExceptionErrorResponse> handleUnauthorized(
      UnauthorizedAccessException ex
   ) {
      ExceptionErrorResponse response = ExceptionErrorResponse.builder()
         .timestamp(LocalDateTime.now())
         .status(HttpStatus.UNAUTHORIZED.value())
         .error("[Unauthorized Error]")
         .message(ex.getMessage())
         .build();

      return ResponseEntity
         .status(HttpStatus.UNAUTHORIZED)
         .body(response);
   }

   @ExceptionHandler(ServerInternalError.class)
   public ResponseEntity<ExceptionErrorResponse> handleGeneric(ServerInternalError ex) {
      String error = ex.getError();
      String message = ex.getMessage();

      ExceptionErrorResponse response = ExceptionErrorResponse.builder()
         .timestamp(LocalDateTime.now())
         .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
         .error((error == null || error.isEmpty()) ? "[Server Internal Error]" : error)
         .message((message == null || message.isEmpty()) ? "Server Internal Error" : message)
         .build();

      return ResponseEntity
         .status(HttpStatus.INTERNAL_SERVER_ERROR)
         .body(response);
   }



   //Validacion de Enum
   @ExceptionHandler(HttpMessageNotReadableException.class)
   public ResponseEntity<ExceptionErrorResponse> handleInvalidEnum(HttpMessageNotReadableException ex) {
      if (ex.getCause() instanceof InvalidFormatException ife) {
         if (ife.getTargetType().isEnum()) {
            ExceptionErrorResponse response = ExceptionErrorResponse.builder()
               .timestamp(LocalDateTime.now())
               .status(HttpStatus.BAD_REQUEST.value())
               .error("[Enum Type]")
               .message("Tipo de Enum no válido: " + ife.getValue())
               .build();

            return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(response);
         }
      }
      return null;
   }
}


/*
{
  "timestamp": "2025-04-11T11:00:00",
  "status": 400,
  "error": "[Request Error]",
  "errors": [
    {
      "field": "username",
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
