package com.ariel.dev22.e_commerce_backend.order.repository;

import com.ariel.dev22.e_commerce_backend.order.models.Order;
import com.ariel.dev22.e_commerce_backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
