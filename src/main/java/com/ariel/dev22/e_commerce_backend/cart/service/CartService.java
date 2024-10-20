package com.ariel.dev22.e_commerce_backend.cart.service;

import com.ariel.dev22.e_commerce_backend.cart.models.Cart;
import com.ariel.dev22.e_commerce_backend.cart.models.CartItem;
import com.ariel.dev22.e_commerce_backend.cart.repository.CartRepository;
import com.ariel.dev22.e_commerce_backend.product.model.Product;
import com.ariel.dev22.e_commerce_backend.product.service.ProductService;
import com.ariel.dev22.e_commerce_backend.user.model.User;
import com.ariel.dev22.e_commerce_backend.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private UserService userService;
    private ProductService productService;

    public String addItem(Long productId, String userEmail) {
        // RECUPERAR O USUARIO
        User user = userService.findUserByEmail(userEmail);

        // RECUPERAR CARRINHO
        Cart cart = user.getCart();

        //RECUPERAR O PRODUTO
        Product product = productService.findById(productId);

        CartItem cartItem = findCartItem(cart.getItems(), product.getId());

        if (cartItem == null) {
            cartItem = new CartItem(product, cart);

            cartItem.setName(product.getName());
            cartItem.setPrice(product.getPrice());
            cartItem.setQuantity(1);
            cartItem.setImageUrl(product.getImageUrl());

            cart.getItems().add(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        cart.updateTotal();
        cartRepository.save(cart);

        return "Item adicionado no carrinho";
    }

    public Cart findCart(String userEmail) {
        User user = userService.findUserByEmail(userEmail);

        return user.getCart();
    }

    public Cart decrementQuantity(Long itemId, String userEmail) {
        User user = userService.findUserByEmail(userEmail);

        Cart cart = user.getCart();

        CartItem cartItem = findCartItem(cart.getItems(), itemId);

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        } else {
            cart.getItems().remove(cartItem);
        }
        cart.updateTotal();

        return cartRepository.save(cart);
    }

    public Cart removeItem(Long itemId, String userEmail) {
        User user = userService.findUserByEmail(userEmail);

        Cart cart = user.getCart();

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(itemId));

        cart.updateTotal();

        return cartRepository.save(cart);
    }

    public Cart emptyCart(String userEmail) {
        User user = userService.findUserByEmail(userEmail);

        Cart cart = user.getCart();

        cart.getItems().removeAll(cart.getItems());

        cart.updateTotal();

        return cartRepository.save(cart);
    }

    private CartItem findCartItem(Set<CartItem> items, Long itemId){
        return items
                .stream()
                .filter(item -> item.getProduct().getId().equals(itemId))
                .findFirst().orElse(null);
    }
}
