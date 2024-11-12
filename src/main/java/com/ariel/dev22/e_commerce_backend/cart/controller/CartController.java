package com.ariel.dev22.e_commerce_backend.cart.controller;

import com.ariel.dev22.e_commerce_backend.cart.dto.CartDTO;
import com.ariel.dev22.e_commerce_backend.cart.dto.CartItemDTO;
import com.ariel.dev22.e_commerce_backend.cart.models.Cart;
import com.ariel.dev22.e_commerce_backend.cart.models.CartItem;
import com.ariel.dev22.e_commerce_backend.cart.service.CartService;
import com.ariel.dev22.e_commerce_backend.product.dto.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductId id,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.addItem(id.productId(), userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<CartDTO> listItems(@AuthenticationPrincipal UserDetails userDetails) {
        Cart cart = cartService.findCart(userDetails.getUsername());

        List<CartItemDTO> items = new ArrayList<>();
        for (CartItem i : cart.getItems()) {
            CartItemDTO item = new CartItemDTO(i.getProduct().getId(), i.getName(), i.getUnitPrice(), i.getQuantity(), i.getImageUrl());

            items.add(item);
        }
        return ResponseEntity.ok(new CartDTO(cart.getId(), cart.getTotal(), items));
    }

    @PutMapping(value = "/decrement")
    public ResponseEntity<CartDTO> decrementItem(@RequestBody ProductId itemId,
                                                 @AuthenticationPrincipal UserDetails userDetails) {
        Cart cart = cartService.decrementQuantity(itemId.productId(), userDetails.getUsername());

        List<CartItemDTO> items = new ArrayList<>();

        for (CartItem i : cart.getItems()) {
            CartItemDTO item = new CartItemDTO(
                    i.getProduct().getId(),
                    i.getName(),
                    i.getUnitPrice(),
                    i.getQuantity(),
                    i.getImageUrl()
            );
            items.add(item);
        }
        return ResponseEntity.ok(new CartDTO(cart.getId(), cart.getTotal(), items));
    }

    @DeleteMapping(value = "/remove/{itemId}")
    public ResponseEntity<CartDTO> removeItem(@PathVariable Long itemId,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        Cart cart = cartService.removeItem(itemId, userDetails.getUsername());

        List<CartItemDTO> items = new ArrayList<>();

        for (CartItem i : cart.getItems()) {
            CartItemDTO item = new CartItemDTO(
                    i.getProduct().getId(),
                    i.getName(),
                    i.getUnitPrice(),
                    i.getQuantity(),
                    i.getImageUrl()
            );
            items.add(item);
        }
        return ResponseEntity.ok(new CartDTO(cart.getId(), cart.getTotal(), items));
    }

    @DeleteMapping(value = "/removeAll")
    public ResponseEntity<CartDTO> removeAllItems(@AuthenticationPrincipal UserDetails userDetails) {
        Cart cart = cartService.emptyCart(userDetails.getUsername());

        return ResponseEntity.ok(new CartDTO(cart.getId(), cart.getTotal(), new ArrayList<>()));
    }
}
