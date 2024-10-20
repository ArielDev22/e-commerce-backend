package com.ariel.dev22.e_commerce_backend.common.exceptions;

import com.ariel.dev22.e_commerce_backend.auth.exception.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(AuthException.class)
    private ResponseEntity<ExceptionHandlerMessage> authException(AuthException exception) {
        return ResponseEntity.badRequest().body(new ExceptionHandlerMessage(exception.getMessage()));
    }
}
