package com.ariel.dev22.e_commerce_backend.domains.cart.mapper;

import com.ariel.dev22.e_commerce_backend.domains.cart.models.dto.CartItemData;
import com.ariel.dev22.e_commerce_backend.domains.cart.models.dto.CartResponse;
import com.ariel.dev22.e_commerce_backend.domains.cart.models.entities.Cart;
import com.ariel.dev22.e_commerce_backend.domains.cart.models.entities.CartItem;

import java.util.stream.Collectors;

public class CartMapper {
    public static CartResponse toCartResponse(Cart cart) {
        return new CartResponse(
                cart.getId(),
                cart.getTotal(),
                cart.getItems().stream().map(CartMapper::toCartItemData).collect(Collectors.toList())
        );
    }

    public static CartItemData toCartItemData(CartItem item) {
        return new CartItemData(
                item.getId().getProduct().getId(),
                item.getName(),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getImageUrl());
    }
}
