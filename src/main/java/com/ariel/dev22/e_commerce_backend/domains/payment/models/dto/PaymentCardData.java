package com.ariel.dev22.e_commerce_backend.domains.payment.models.dto;

import com.ariel.dev22.e_commerce_backend.domains.card.model.CardData;

public record PaymentCardData(String method, PaymentShippingData shippingData, CardData cardData) {
}
