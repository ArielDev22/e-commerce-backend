package com.ariel.dev22.e_commerce_backend.security;

import com.ariel.dev22.e_commerce_backend.auth.service.AuthService;
import com.ariel.dev22.e_commerce_backend.token.service.RevokedTokenService;
import com.ariel.dev22.e_commerce_backend.token.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private RevokedTokenService revokedTokenService;
    private TokenService tokenService;
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);

        if (token != null) {
            String email = tokenService.validateToken(token);

            if (email != null) {
                UserDetails user = authService.loadUserByUsername(email);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
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
