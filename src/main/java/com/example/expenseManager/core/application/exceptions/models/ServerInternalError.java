package com.example.expenseManager.core.application.exceptions.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ServerInternalError extends RuntimeException{
   String message;
    public ServerInternalError(String message) {
        this.message = message;
    }
}
