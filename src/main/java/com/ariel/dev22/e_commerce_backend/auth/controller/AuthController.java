package com.ariel.dev22.e_commerce_backend.auth.controller;

import com.ariel.dev22.e_commerce_backend.auth.dto.AuthResponse;
import com.ariel.dev22.e_commerce_backend.auth.dto.LoginData;
import com.ariel.dev22.e_commerce_backend.auth.dto.RegisterData;
import com.ariel.dev22.e_commerce_backend.token.service.RevokedTokenService;
import com.ariel.dev22.e_commerce_backend.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@AllArgsConstructor
public class AuthController {
    private UserService userService;
    private RevokedTokenService revokedTokenService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginData data) {
        return ResponseEntity.ok(new AuthResponse(userService.login(data)));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterData registerData) {
        return ResponseEntity.ok(new AuthResponse(userService.register(registerData.toModel())));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");

        return ResponseEntity.ok(revokedTokenService.revokeToken(token));
    }
}
