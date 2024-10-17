package com.ariel.dev22.e_commerce_backend.cart;

import java.math.BigDecimal;

public record CartItemDTO(Long id, String name, BigDecimal price, Integer quantity, String imageUrl) {
}
