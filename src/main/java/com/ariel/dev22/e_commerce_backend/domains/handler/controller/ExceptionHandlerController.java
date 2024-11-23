package com.ariel.dev22.e_commerce_backend.domains.handler.controller;

import com.ariel.dev22.e_commerce_backend.domains.auth.exceptions.AuthException;
import com.ariel.dev22.e_commerce_backend.domains.card.exception.CardException;
import com.ariel.dev22.e_commerce_backend.domains.email.exception.EmailException;
import com.ariel.dev22.e_commerce_backend.domains.handler.dto.ErrorMessage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> argumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorTitle = "Argumento inválido";
        String errorMessage = exception.getBindingResult().getFieldError().getDefaultMessage();

        ErrorMessage message = new ErrorMessage(
                status, errorTitle, errorMessage, request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(EntityNotFoundException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Recurso não encontrado";

        ErrorMessage message = new ErrorMessage(
                status, error, exception.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorMessage> authException(AuthException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String error = "Credenciais inválidas";

        ErrorMessage message = new ErrorMessage(
                status, error, exception.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<ErrorMessage> emailException(EmailException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String error = "Erro no serviço de email";

        ErrorMessage message = new ErrorMessage(
                status, error, exception.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(CardException.class)
    public ResponseEntity<ErrorMessage> cardException(CardException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String error = "Dados inválidos";

        ErrorMessage message = new ErrorMessage(
                status, error, exception.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(message);
    }
}