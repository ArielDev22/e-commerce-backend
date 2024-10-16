package com.ariel.dev22.e_commerce_backend.product;

import java.math.BigDecimal;

public record ProductDetailDTO(Long id, String name, BigDecimal price, String imageUrl, String details) {
}
