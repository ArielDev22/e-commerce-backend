package com.ariel.dev22.e_commerce_backend.order.service;

import com.ariel.dev22.e_commerce_backend.cart.models.Cart;
import com.ariel.dev22.e_commerce_backend.cart.models.CartItem;
import com.ariel.dev22.e_commerce_backend.cart.service.CartService;
import com.ariel.dev22.e_commerce_backend.order.models.Order;
import com.ariel.dev22.e_commerce_backend.order.models.OrderItem;
import com.ariel.dev22.e_commerce_backend.order.repository.OrderRepository;
import com.ariel.dev22.e_commerce_backend.user.model.User;
import com.ariel.dev22.e_commerce_backend.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private UserService userService;
    private CartService cartService;

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
                    item.getPrice(),
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
