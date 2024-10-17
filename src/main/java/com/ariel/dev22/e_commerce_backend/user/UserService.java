package com.ariel.dev22.e_commerce_backend.user;

import com.ariel.dev22.e_commerce_backend.cart.Cart;
import com.ariel.dev22.e_commerce_backend.favorite.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String name, String email, String password) {
        // CRAIR NOVO USUARIO
        User newUser = new User(name, email, password, "user");

        // CRIAR O FAVORITOS DO USUARIO
        Favorite favorite = new Favorite();
        favorite.setUser(newUser);
        newUser.setFavorite(favorite);

        // CRIAR O CARRINHO DO USUARIO
        Cart cart = new Cart(newUser);
        newUser.setCart(cart);

        return userRepository.save(newUser);
    }

    public User findUserByEmail(String email) {
        return (User) userRepository.findByEmail(email);
    }
}
