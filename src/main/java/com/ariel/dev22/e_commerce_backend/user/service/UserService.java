package com.ariel.dev22.e_commerce_backend.user.service;

import com.ariel.dev22.e_commerce_backend.auth.dto.LoginData;
import com.ariel.dev22.e_commerce_backend.auth.exception.AuthException;
import com.ariel.dev22.e_commerce_backend.cart.models.Cart;
import com.ariel.dev22.e_commerce_backend.email.EmailService;
import com.ariel.dev22.e_commerce_backend.favorite.models.Favorite;
import com.ariel.dev22.e_commerce_backend.token.service.TokenService;
import com.ariel.dev22.e_commerce_backend.user.model.User;
import com.ariel.dev22.e_commerce_backend.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private EmailService emailService;
    private PasswordEncoder encoder;
    private TokenService tokenService;

    public String register(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            // CODIFICAR SENHA
            user.setPassword(encoder.encode(user.getPassword()));

            // CRIAR O FAVORITOS DO USUARIO
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            user.setFavorite(favorite);

            // CRIAR O CARRINHO DO USUARIO
            Cart cart = new Cart(user);
            user.setCart(cart);

            User newUser = userRepository.save(user);

            emailService.confirmationEmailGeneration(newUser.getEmail());

            return tokenService.generateToken(user);
        }
        throw new AuthException("Já existe uma conta com este email");
    }

    public String login(LoginData data) {
        User user = (User) userRepository.findByEmail(data.email());

        if (user != null) {
            if (encoder.matches(data.password(), user.getPassword())) {
                return tokenService.generateToken(user);
            } else {
                throw new AuthException("Senha incorreta");
            }
        }
        throw new AuthException("Não existe uma conta com este email");
    }

    public User findUserByEmail(String email) {
        return (User) userRepository.findByEmail(email);
    }
}
