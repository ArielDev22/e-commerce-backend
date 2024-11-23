package com.ariel.dev22.e_commerce_backend.domains.user.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UpdateUserRequest(
        @NotBlank
        String name,
        @NotBlank
        String email,
        String telephone,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate birthdate) {
}
