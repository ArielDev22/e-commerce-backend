package com.ariel.dev22.e_commerce_backend.domains.card.model;

import jakarta.validation.constraints.NotBlank;

public record CardData(
        @NotBlank(message = "Número inválido")
        String number,
        @NotBlank(message = "Data de expiração inválida")
        String expirationDate,
        @NotBlank(message = "Cvc inválido")
        String cvc) {
}
