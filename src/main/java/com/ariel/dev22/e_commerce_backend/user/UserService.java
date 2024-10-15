package com.ariel.dev22.e_commerce_backend.user;

import com.ariel.dev22.e_commerce_backend.favorite.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String name, String email, String password){
        User newUser = new User(name, email, password, "user");

        Favorite favorite = new Favorite();
        favorite.setUser(newUser);

        newUser.setFavorite(favorite);

        return userRepository.save(newUser);
    }
}
