package com.ariel.dev22.e_commerce_backend.auth;

import com.ariel.dev22.e_commerce_backend.user.User;

public record AuthRegisterDTO(String email, String name, String password, String confPassword) {
    public User toModel(){
        return new User(email, name, password);
    }
}
