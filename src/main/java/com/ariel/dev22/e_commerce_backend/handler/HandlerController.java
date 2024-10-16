package com.ariel.dev22.e_commerce_backend.handler;

import com.ariel.dev22.e_commerce_backend.auth.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerController {
    @ExceptionHandler(AuthException.class)
    private ResponseEntity<HandlerMessage> authException(AuthException exception){
        return ResponseEntity.badRequest().body(new HandlerMessage(exception.getMessage()));
    }
}
