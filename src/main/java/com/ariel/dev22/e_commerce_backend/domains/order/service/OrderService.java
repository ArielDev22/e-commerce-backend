package com.ariel.dev22.e_commerce_backend.domains.order.service;

import com.ariel.dev22.e_commerce_backend.domains.cart.models.entities.Cart;
import com.ariel.dev22.e_commerce_backend.domains.cart.models.entities.CartItem;
import com.ariel.dev22.e_commerce_backend.domains.cart.service.CartService;
import com.ariel.dev22.e_commerce_backend.domains.order.models.entities.Order;
import com.ariel.dev22.e_commerce_backend.domains.order.models.entities.OrderItem;
import com.ariel.dev22.e_commerce_backend.domains.order.repository.OrderRepository;
import com.ariel.dev22.e_commerce_backend.domains.user.model.User;
import com.ariel.dev22.e_commerce_backend.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;

    public Order createOrder(String userEmail) {
        User user = userService.findUserByEmail(userEmail);

        Cart cart = user.getCart();

        Order order = new Order();

        order.setUser(user);
        order.setTotal(cart.getTotal());
        order.setDate(LocalDate.now());
        order.setStatus(1);
        order.setPayment(null);

        for (CartItem item : cart.getItems()) {
            OrderItem orderItem = new OrderItem(
                    item.getProduct(),
                    order,
                    item.getName(),
                    item.getUnitPrice(),
                    item.getQuantity()
            );
            order.getItems().add(orderItem);
        }

        orderRepository.save(order);
        cartService.emptyCart(userEmail);

        return order;
    }

    public List<Order> listOrders(String userEmail) {
        return orderRepository.findAllByUser(userService.findUserByEmail(userEmail));
    }
}
