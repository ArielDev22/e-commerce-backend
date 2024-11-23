package com.ariel.dev22.e_commerce_backend.domains.payment.models.dto;

public record PaymentShippingData(
        String email,
        String name,
        String street,
        String city,
        String state,
        String postalCode,
        String phone) {
}
