package com.ariel.dev22.e_commerce_backend.auth.dto;

import com.ariel.dev22.e_commerce_backend.user.model.User;

public record AuthRegisterDTO(String email, String name, String password, String confPassword) {
    public User toModel(){
        return new User(name, email, password);
    }
}
