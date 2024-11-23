package com.ariel.dev22.e_commerce_backend.domains.product.model.dto;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        String name,
        BigDecimal price,
        String imageUrl
) {
}
