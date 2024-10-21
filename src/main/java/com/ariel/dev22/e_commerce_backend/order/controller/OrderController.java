package com.ariel.dev22.e_commerce_backend.order.controller;

import com.ariel.dev22.e_commerce_backend.order.models.Order;
import com.ariel.dev22.e_commerce_backend.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value = "/create")
    public ResponseEntity<Order> createOrder(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(userDetails.getUsername()));
    }

    @GetMapping()
    public ResponseEntity<List<Order>> listMyOrders(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(orderService.listOrders(userDetails.getUsername()));
    }
}
