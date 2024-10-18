package com.ariel.dev22.e_commerce_backend.auth;

import com.ariel.dev22.e_commerce_backend.token.TokenService;
import com.ariel.dev22.e_commerce_backend.token.revoked.RevokedTokenService;
import com.ariel.dev22.e_commerce_backend.user.User;
import com.ariel.dev22.e_commerce_backend.user.UserService;
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
