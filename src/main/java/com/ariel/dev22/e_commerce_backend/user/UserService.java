package com.ariel.dev22.e_commerce_backend.user;

import com.ariel.dev22.e_commerce_backend.auth.AuthException;
import com.ariel.dev22.e_commerce_backend.cart.Cart;
import com.ariel.dev22.e_commerce_backend.favorite.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            // DECODIFICAR SENHA
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            // SETAR ROLE
            user.setRole(UserRole.getRoleOf("user"));

            // CRIAR O FAVORITOS DO USUARIO
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            user.setFavorite(favorite);

            // CRIAR O CARRINHO DO USUARIO
            Cart cart = new Cart(user);
            user.setCart(cart);

            return userRepository.save(user);
        }
        throw new AuthException("Já existe uma conta com este email");
    }

    public User verifyLoginData(String email, String password){
        User user = (User) userRepository.findByEmail(email);

        if (user != null){
            if (encoder.matches(password, user.getPassword())){
                return user;
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
