package com.ariel.dev22.e_commerce_backend.payment.dto;

public record CardData(String number,
                       String expirationDate,
                       String cvc) {
}
