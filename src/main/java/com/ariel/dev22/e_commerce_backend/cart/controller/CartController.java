package com.ariel.dev22.e_commerce_backend.cart.controller;

import com.ariel.dev22.e_commerce_backend.cart.dto.CartDTO;
import com.ariel.dev22.e_commerce_backend.cart.models.Cart;
import com.ariel.dev22.e_commerce_backend.cart.service.CartService;
import com.ariel.dev22.e_commerce_backend.product.dto.ProductId;
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
    public ResponseEntity<CartDTO> listItems(@AuthenticationPrincipal UserDetails userDetails) {
        Cart cart = cartService.findCart(userDetails.getUsername());

        return ResponseEntity.ok(new CartDTO(cart.getId(), cart.getTotal(), cartService.listCartItems(cart)));
    }

    @PutMapping(value = "/increment")
    public ResponseEntity<CartDTO> incrementItem(@RequestBody ProductId itemId,
                                                 @AuthenticationPrincipal UserDetails userDetails) {
        Cart cart = cartService.incrementQuantity(itemId.productId(), userDetails.getUsername());

        return ResponseEntity.ok(new CartDTO(cart.getId(), cart.getTotal(), cartService.listCartItems(cart)));
    }

    @PutMapping(value = "/decrement")
    public ResponseEntity<CartDTO> decrementItem(@RequestBody ProductId itemId,
                                                 @AuthenticationPrincipal UserDetails userDetails) {
        Cart cart = cartService.decrementQuantity(itemId.productId(), userDetails.getUsername());

        return ResponseEntity.ok(new CartDTO(cart.getId(), cart.getTotal(), cartService.listCartItems(cart)));
    }

    @DeleteMapping(value = "/remove/{itemId}")
    public ResponseEntity<CartDTO> removeItem(@PathVariable Long itemId,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        Cart cart = cartService.removeItem(itemId, userDetails.getUsername());

        return ResponseEntity.ok(new CartDTO(cart.getId(), cart.getTotal(), cartService.listCartItems(cart)));
    }

    @DeleteMapping(value = "/removeAll")
    public ResponseEntity<CartDTO> removeAllItems(@AuthenticationPrincipal UserDetails userDetails) {
        Cart cart = cartService.emptyCart(userDetails.getUsername());

        return ResponseEntity.ok(new CartDTO(cart.getId(), cart.getTotal(), new ArrayList<>()));
    }
}
