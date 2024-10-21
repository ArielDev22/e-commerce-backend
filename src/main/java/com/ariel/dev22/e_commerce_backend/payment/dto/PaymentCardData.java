package com.ariel.dev22.e_commerce_backend.payment.dto;

public record PaymentCardData(String method, PaymentShippingData shippingData, CardData cardData) {
}
