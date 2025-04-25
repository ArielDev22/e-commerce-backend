package com.ariel.dev22.e_commerce_backend.domains.user.model.dto;

import com.ariel.dev22.e_commerce_backend.domains.address.models.Address;

public record UserData(
        Long id,
        String name,
        String email,
        String telephone,
        String birthdate,
        byte[] image,
        Address address
) {
}
