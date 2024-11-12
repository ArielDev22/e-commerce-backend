package com.ariel.dev22.e_commerce_backend.product.dto;

import java.math.BigDecimal;

public record ProductsCategory(Long id, String name, BigDecimal price, String imageUrl) {
}
