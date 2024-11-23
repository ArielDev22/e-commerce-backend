package com.ariel.dev22.e_commerce_backend.domains.payment.exception;

public class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }
}
