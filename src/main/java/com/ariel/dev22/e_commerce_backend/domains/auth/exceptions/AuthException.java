package com.ariel.dev22.e_commerce_backend.domains.auth.exceptions;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
