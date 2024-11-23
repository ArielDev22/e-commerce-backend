package com.ariel.dev22.e_commerce_backend.domains.handler.dto;

import org.springframework.http.HttpStatus;

public record ErrorMessage(HttpStatus status, String error, String message, String url) {
}
