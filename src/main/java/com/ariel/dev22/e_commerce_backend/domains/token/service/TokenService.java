package com.ariel.dev22.e_commerce_backend.domains.token.service;

import com.ariel.dev22.e_commerce_backend.domains.token.exception.TokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    private final RevokedTokenService revokedTokenService;

    public String generateToken(UserDetails userDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT
                    .create()
                    .withIssuer("e-commerce-backend")
                    .withSubject(userDetails.getUsername())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            System.out.println("Token gerado: " + token);
            return token;
        } catch (JWTCreationException e) {
            System.out.println(e.getMessage());
            throw new TokenException("Falha ao gerar o token.");
        }
    }

    public String validateToken(String token) {
        try {
            if (revokedTokenService.isRevoked(token)) return null;

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT
                    .require(algorithm)
                    .withIssuer("e-commerce-backend")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
