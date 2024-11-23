package com.ariel.dev22.e_commerce_backend.domains.order.repository;

import com.ariel.dev22.e_commerce_backend.domains.order.models.entities.Order;
import com.ariel.dev22.e_commerce_backend.domains.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
