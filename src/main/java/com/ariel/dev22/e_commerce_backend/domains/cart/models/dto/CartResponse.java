package com.ariel.dev22.e_commerce_backend.domains.cart.models.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartResponse(Long id, BigDecimal total, List<CartItemData> items) {
}
