package com.ariel.dev22.e_commerce_backend.user.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    USER("user");

    private String value;

    public static UserRole getRoleOf(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.getValue().equalsIgnoreCase(role)) return userRole;
        }
        return null;
    }
}
