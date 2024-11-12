package com.ariel.dev22.e_commerce_backend.token.service;

import com.ariel.dev22.e_commerce_backend.token.model.RevokedToken;
import com.ariel.dev22.e_commerce_backend.token.repository.RevokedTokenRepository;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RevokedTokenService {
    @Autowired
    private RevokedTokenRepository revokedTokenRepository;

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
