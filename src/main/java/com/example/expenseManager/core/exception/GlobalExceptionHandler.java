package com.example.expenseManager.core.exception;

import com.example.expenseManager.core.exception.models.ServerInternalError;
import com.example.expenseManager.core.validation.ValidationErrorResponse;
import com.example.expenseManager.core.validation.dto.FieldErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
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

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ValidationErrorResponse> handleValidationException(
      MethodArgumentNotValidException ex,
      HttpServletRequest request
   ) {
      List<FieldErrorDTO> errors = ex.getBindingResult().getFieldErrors()
         .stream()
         .map(err -> new FieldErrorDTO(err.getField(), err.getDefaultMessage()))
         .toList();

      ValidationErrorResponse response = new ValidationErrorResponse(
         LocalDateTime.now(),
         HttpStatus.BAD_REQUEST.value(),
         "[Request Error]",
         errors,
         request.getRequestURI()
      );

      return ResponseEntity.badRequest().body(response);
   }


   //Validaciones Generica
   @ExceptionHandler(ServerInternalError.class)
   public ResponseEntity<Map<String, String>> handleGeneric(ServerInternalError ex) {
      return ResponseEntity
         .status(HttpStatus.INTERNAL_SERVER_ERROR)
         .body(Map.of(
            "error", "[Server Error]",
            "message", "Server Internal Error,"
         ));
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
  "path": "/users"
}
 */
