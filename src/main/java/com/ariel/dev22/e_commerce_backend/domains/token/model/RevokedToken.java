package com.ariel.dev22.e_commerce_backend.domains.token.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@Entity
@Table(name = "revoked_tokens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class RevokedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @DateTimeFormat(pattern = "dd/MM/yyy HH:mm:ss")
    private Instant expirationDate;

    public RevokedToken(String token, Instant expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }
}
