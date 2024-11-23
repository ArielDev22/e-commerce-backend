package com.ariel.dev22.e_commerce_backend.domains.cart.service;

import com.ariel.dev22.e_commerce_backend.domains.cart.mapper.CartMapper;
import com.ariel.dev22.e_commerce_backend.domains.cart.models.dto.CartItemData;
import com.ariel.dev22.e_commerce_backend.domains.cart.models.entities.Cart;
import com.ariel.dev22.e_commerce_backend.domains.cart.models.entities.CartItem;
import com.ariel.dev22.e_commerce_backend.domains.cart.repository.CartRepository;
import com.ariel.dev22.e_commerce_backend.domains.product.model.entity.Product;
import com.ariel.dev22.e_commerce_backend.domains.product.service.ProductService;
import com.ariel.dev22.e_commerce_backend.domains.user.model.User;
import com.ariel.dev22.e_commerce_backend.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    public String addItem(Long productId, String userEmail) {
        // RECUPERAR O USUARIO
        User user = userService.findUserByEmail(userEmail);

        // RECUPERAR CARRINHO
        Cart cart = user.getCart();

        //RECUPERAR O PRODUTO
        Product product = productService.findById(productId);

        // CRIAR E ADICIONAR ITEM NO CARRINHO
        CartItem cartItem = new CartItem(product, cart);

        cartItem.setName(product.getName());
        cartItem.setUnitPrice(product.getUnitPrice());
        cartItem.setQuantity(1);
        cartItem.setImageUrl(product.getImageUrl());

        cart.getItems().add(cartItem);

        // ATUALIZAR CARRINHO
        cart.updateTotal();
        cartRepository.save(cart);

        return "Item adicionado no carrinho";
    }

    public Cart findCart(String userEmail) {
        return userService.findUserByEmail(userEmail).getCart();
    }

    public Cart incrementQuantity(Long itemId, String userEmail) {
        User user = userService.findUserByEmail(userEmail);

        Cart cart = user.getCart();

        CartItem cartItem = findCartItem(cart.getItems(), itemId);

        cartItem.setQuantity(cartItem.getQuantity() + 1);

        cart.updateTotal();

        return cartRepository.save(cart);
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

    public List<CartItemData> listCartItems(Cart cart) {
        List<CartItemData> items = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            items.add(CartMapper.toCartItemData(item));
        }
        return items;
    }

    private CartItem findCartItem(Set<CartItem> items, Long itemId) {
        return items
                .stream()
                .filter(item -> item.getProduct().getId().equals(itemId))
                .findFirst().orElse(null);
    }
}
