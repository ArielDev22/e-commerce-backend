package com.ariel.dev22.e_commerce_backend.domains.address.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressInfo(
        @NotBlank(message = "Este campo é obrigatório")
        String street,
        @NotBlank(message = "Este campo é obrigatório")
        String district,
        @NotBlank(message = "Este campo é obrigatório")
        String city,
        @NotBlank(message = "Este campo é obrigatório")
        String number,
        @NotBlank(message = "Este campo é obrigatório")
        @Size(min = 8, max = 8, message = "CEP inválido")
        String cep,
        String reference
) {
}
