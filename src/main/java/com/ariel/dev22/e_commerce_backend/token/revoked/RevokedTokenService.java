package com.ariel.dev22.e_commerce_backend.token.revoked;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class RevokedTokenService {
    @Autowired
    private RevokedTokenRepository revokedTokenRepository;

    public void revokeToken(String token){
        RevokedToken revokedToken = new RevokedToken(token, extractExpirationDate(token));

        revokedTokenRepository.save(revokedToken);
    }

    private LocalDateTime extractExpirationDate(String token) {
        Instant instant = JWT.decode(token).getExpiresAtAsInstant();

        return instant.atZone(ZoneOffset.UTC).toLocalDateTime();
    }

    public boolean isRevoked(String token){
        return revokedTokenRepository.findByToken(token).isPresent();
    }
}
