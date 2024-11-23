package com.ariel.dev22.e_commerce_backend.domains.token.service;

import com.ariel.dev22.e_commerce_backend.domains.token.model.RevokedToken;
import com.ariel.dev22.e_commerce_backend.domains.token.repository.RevokedTokenRepository;
import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RevokedTokenService {
    private final RevokedTokenRepository revokedTokenRepository;

    public String revokeToken(String token) {
        RevokedToken revokedToken = new RevokedToken(token, extractExpirationDate(token));

        revokedTokenRepository.save(revokedToken);

        return "Token revogado com sucesso: " + token;
    }

    public boolean isRevoked(String token) {
        return revokedTokenRepository.findByToken(token).isPresent();
    }

    private Instant extractExpirationDate(String token) {
        return JWT.decode(token).getExpiresAtAsInstant();
    }
}
