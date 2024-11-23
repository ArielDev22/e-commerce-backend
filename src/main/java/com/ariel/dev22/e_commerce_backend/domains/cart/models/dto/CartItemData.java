package com.ariel.dev22.e_commerce_backend.domains.cart.models.dto;

import java.math.BigDecimal;

public record CartItemData(Long id, String name, BigDecimal price, Integer quantity, String imageUrl) {
}
