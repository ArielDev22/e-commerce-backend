package com.ariel.dev22.e_commerce_backend.domains.cart.repository;

import com.ariel.dev22.e_commerce_backend.domains.cart.models.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
