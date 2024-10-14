package com.ariel.dev22.e_commerce_backend.auth;

import com.ariel.dev22.e_commerce_backend.token.TokenService;
import com.ariel.dev22.e_commerce_backend.token.revoked.RevokedTokenService;
import com.ariel.dev22.e_commerce_backend.user.User;
import com.ariel.dev22.e_commerce_backend.user.UserRepository;
import com.ariel.dev22.e_commerce_backend.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;
    private final UserService userService;
    private final RevokedTokenService revokedTokenService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthLoginDTO login) {
        UserDetails userDetails = userRepository.findByEmail(login.email());

        if (userDetails != null) {
            if (encoder.matches(login.password(), userDetails.getPassword())) {
                String token = tokenService.generateToken(userDetails);

                return ResponseEntity.ok(new AuthResponseDTO(token));
            } else {
                throw new AuthException("Senha incorreta");
            }
        } else {
            throw new AuthException("Não existe uma conta com este email");
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody AuthRegisterDTO register) {
        UserDetails userDetails = userRepository.findByEmail(register.email());

        if (userDetails == null) {
            String encodedPassword = encoder.encode(register.password());

            User newUser = userService.createUser(register.name(), register.email(), encodedPassword);

            String token = tokenService.generateToken(newUser);

            return ResponseEntity.ok(new AuthResponseDTO(token));
        } else {
            throw new AuthException("Já existe uma conta com este email");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");

        revokedTokenService.revokeToken(token);

        return ResponseEntity.ok().build();
    }
}
