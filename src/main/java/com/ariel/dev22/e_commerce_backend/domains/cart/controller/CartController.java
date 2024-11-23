package com.ariel.dev22.e_commerce_backend.domains.cart.controller;

import com.ariel.dev22.e_commerce_backend.domains.cart.mapper.CartMapper;
import com.ariel.dev22.e_commerce_backend.domains.cart.models.dto.CartResponse;
import com.ariel.dev22.e_commerce_backend.domains.cart.models.entities.Cart;
import com.ariel.dev22.e_commerce_backend.domains.cart.service.CartService;
import com.ariel.dev22.e_commerce_backend.domains.product.model.dto.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public ResponseEntity<CartResponse> listItems(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(CartMapper.toCartResponse(cartService.findCart(userDetails.getUsername())));
    }

    @PutMapping(value = "/increment")
    public ResponseEntity<CartResponse> incrementItem(@RequestBody ProductId itemId,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(CartMapper.toCartResponse(cartService.incrementQuantity(itemId.productId(), userDetails.getUsername())));
    }

    @PutMapping(value = "/decrement")
    public ResponseEntity<CartResponse> decrementItem(@RequestBody ProductId itemId,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(CartMapper.toCartResponse(cartService.decrementQuantity(itemId.productId(), userDetails.getUsername())));
    }

    @DeleteMapping(value = "/remove/{itemId}")
    public ResponseEntity<CartResponse> removeItem(@PathVariable Long itemId,
                                                   @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(CartMapper.toCartResponse(cartService.removeItem(itemId, userDetails.getUsername())));
    }

    @DeleteMapping(value = "/removeAll")
    public ResponseEntity<CartResponse> removeAllItems(@AuthenticationPrincipal UserDetails userDetails) {
        Cart cart = cartService.emptyCart(userDetails.getUsername());

        return ResponseEntity.ok(new CartResponse(cart.getId(), cart.getTotal(), new ArrayList<>()));
    }
}
