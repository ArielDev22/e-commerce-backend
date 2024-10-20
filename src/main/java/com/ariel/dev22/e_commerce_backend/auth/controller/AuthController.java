package com.ariel.dev22.e_commerce_backend.auth.controller;

import com.ariel.dev22.e_commerce_backend.auth.dto.AuthLoginDTO;
import com.ariel.dev22.e_commerce_backend.auth.dto.AuthRegisterDTO;
import com.ariel.dev22.e_commerce_backend.auth.dto.AuthResponse;
import com.ariel.dev22.e_commerce_backend.auth.service.AuthService;
import com.ariel.dev22.e_commerce_backend.token.service.TokenService;
import com.ariel.dev22.e_commerce_backend.token.service.RevokedTokenService;
import com.ariel.dev22.e_commerce_backend.user.model.User;
import com.ariel.dev22.e_commerce_backend.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final UserService userService;
    private final RevokedTokenService revokedTokenService;
    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginDTO login) {
        User user = userService.verifyLoginData(login.email(), login.password());

        String token = tokenService.generateToken(authService.loadUserByUsername(user.getUsername()));

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRegisterDTO authRegisterDTO) {
        User user = authRegisterDTO.toModel();

        User userRegistered = userService.registerUser(user);

        String token = tokenService.generateToken(authService.loadUserByUsername(userRegistered.getUsername()));

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");

        revokedTokenService.revokeToken(token);

        return ResponseEntity.ok().build();
    }
}
