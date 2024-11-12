package com.ariel.dev22.e_commerce_backend.common.exceptions;

import org.springframework.http.HttpStatus;

public record ExceptionHandlerMessage(String message, HttpStatus status) {
}
