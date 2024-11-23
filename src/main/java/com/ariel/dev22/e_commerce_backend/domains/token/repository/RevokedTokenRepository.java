package com.ariel.dev22.e_commerce_backend.domains.token.repository;

import com.ariel.dev22.e_commerce_backend.domains.token.model.RevokedToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RevokedTokenRepository extends JpaRepository<RevokedToken, Long> {
    Optional<RevokedToken> findByToken(String token);
}
