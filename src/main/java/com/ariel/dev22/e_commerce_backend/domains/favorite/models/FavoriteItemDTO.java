package com.ariel.dev22.e_commerce_backend.domains.favorite.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FavoriteItemDTO(Long id, String name, BigDecimal price, String imageUrl, LocalDate addedAt) {
}
