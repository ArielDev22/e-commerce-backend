package com.ariel.dev22.e_commerce_backend.infra.security;

import com.ariel.dev22.e_commerce_backend.domains.auth.exceptions.AuthException;
import com.ariel.dev22.e_commerce_backend.domains.auth.service.AuthService;
import com.ariel.dev22.e_commerce_backend.domains.token.service.RevokedTokenService;
import com.ariel.dev22.e_commerce_backend.domains.token.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final RevokedTokenService revokedTokenService;
    private final TokenService tokenService;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);

        if (token != null) {
            String email = tokenService.validateToken(token);

            if (email != null) {
                UserDetails user = authService.loadUserByUsername(email);

                if (user != null){
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new AuthException("Usuário não registrado ou autenticado.");
                }
            } else {
                System.out.println(revokedTokenService.revokeToken(token));
            }
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        else return authHeader.replace("Bearer ", "");
    }
}
