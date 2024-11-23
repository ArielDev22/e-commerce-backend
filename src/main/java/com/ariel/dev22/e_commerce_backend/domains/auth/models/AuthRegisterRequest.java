package com.ariel.dev22.e_commerce_backend.domains.auth.models;

import com.ariel.dev22.e_commerce_backend.domains.user.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRegisterRequest(
        @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "O e-mail deve estar em um formato válido")
        String email,
        @NotBlank(message = "O nome não pode estar em branco.")
        String name,
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String password,
        String confPassword) {
    public User toModel() {
        return new User(name, email, password, "user");
    }
}
