package com.ariel.dev22.e_commerce_backend.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    USER("user");

    private String roleValue;

    public static UserRole getRoleOf(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.getRoleValue().equals(role)) {
                return userRole;
            }
        }
        return null;
    }
}
