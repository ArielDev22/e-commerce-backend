package com.ariel.dev22.e_commerce_backend.cart.repository;

import com.ariel.dev22.e_commerce_backend.cart.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
