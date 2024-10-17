package com.ariel.dev22.e_commerce_backend.cart;

import java.math.BigDecimal;
import java.util.List;

public record CartDTO(Long id, BigDecimal total, List<CartItemDTO> items) {
}
