package com.ariel.dev22.e_commerce_backend.domains.user.service;

import com.ariel.dev22.e_commerce_backend.domains.address.models.Address;
import com.ariel.dev22.e_commerce_backend.domains.auth.exceptions.AuthException;
import com.ariel.dev22.e_commerce_backend.domains.auth.models.AuthLoginRequest;
import com.ariel.dev22.e_commerce_backend.domains.cart.models.entities.Cart;
import com.ariel.dev22.e_commerce_backend.domains.email.service.EmailService;
import com.ariel.dev22.e_commerce_backend.domains.favorite.models.Favorite;
import com.ariel.dev22.e_commerce_backend.domains.token.service.TokenService;
import com.ariel.dev22.e_commerce_backend.domains.user.model.User;
import com.ariel.dev22.e_commerce_backend.domains.user.model.UserImage;
import com.ariel.dev22.e_commerce_backend.domains.user.model.dto.UpdateUserRequest;
import com.ariel.dev22.e_commerce_backend.domains.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${profileImage.dir}")
    private String standardProfileImageDir;

    public User findUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    public String register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            // CODIFICAR SENHA
            user.setPassword(encoder.encode(user.getPassword()));

            // CRIAR O FAVORITOS DO USUARIO
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            user.setFavorite(favorite);

            // CRIAR O CARRINHO DO USUARIO
            Cart cart = new Cart(user);
            user.setCart(cart);

            // CRIAR ENDEREÇO
            Address address = new Address();
            address.setUser(user);
            user.setAddress(address);

            // SETAR IMAGEM
            UserImage imageProfile = new UserImage();
            imageProfile.setImageUrl(standardProfileImageDir);
            imageProfile.setUser(user);

            user.setProfileImage(imageProfile);

            User newUser = userRepository.save(user);

            emailService.confirmationEmailGeneration(newUser.getEmail());

            return tokenService.generateToken(newUser);
        }
        throw new AuthException("Já existe uma conta com este email");
    }

    public String login(AuthLoginRequest request) {
        User userRegistered = userRepository.findByEmail(request.email()).orElse(null);

        if (userRegistered != null) {
            if (encoder.matches(request.password(), userRegistered.getPassword())) {
                return tokenService.generateToken(userRegistered);
            } else {
                throw new AuthException("Senha incorreta");
            }
        }
        throw new AuthException("Não existe uma conta com este email");
    }

    public String updateUserData(UpdateUserRequest request) {
        User user = findUserByEmail(request.email());

        user.setName(request.name());
        user.setEmail(request.email());
        user.setTelephone(request.telephone());
        user.setBirthdate(request.birthdate());

        userRepository.save(user);

        return "Informações atualizadas";
    }

    public String uploadProfileImage(String email, MultipartFile image) {
        User user = findUserByEmail(email);

        String archive = "profile_" + user.getId() + "_" + image.getOriginalFilename();
        Path archiveDirectory = Path.of(uploadDir, archive);

        try {
            Files.copy(image.getInputStream(), archiveDirectory, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagen: " + e.getMessage());
        }

        UserImage userImage = user.getProfileImage();
        userImage.setImageUrl(archiveDirectory.toString());

        user.setProfileImage(userImage);

        userRepository.save(user);

        return "Imagem salva";
    }
}
