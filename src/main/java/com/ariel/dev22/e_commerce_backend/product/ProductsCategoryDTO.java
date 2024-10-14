package com.ariel.dev22.e_commerce_backend.product;

import java.math.BigDecimal;

public record ProductsCategoryDTO(Long id, String name, BigDecimal price, String imageUrl) {
}
