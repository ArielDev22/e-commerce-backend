package com.ariel.dev22.e_commerce_backend.common.exceptions;

import com.ariel.dev22.e_commerce_backend.auth.exception.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionHandlerMessage> authException(AuthException exception) {
        ExceptionHandlerMessage message = new ExceptionHandlerMessage(exception.getMessage(), HttpStatus.BAD_REQUEST);

        return ResponseEntity.badRequest().body(message);
    }
}
