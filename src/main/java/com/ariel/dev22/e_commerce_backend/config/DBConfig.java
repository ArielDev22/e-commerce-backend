package com.ariel.dev22.e_commerce_backend.config;

import com.ariel.dev22.e_commerce_backend.user.User;
import com.ariel.dev22.e_commerce_backend.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DBConfig {
    @Bean
    CommandLineRunner initDB(UserRepository userRepository){
        return args -> {
            PasswordEncoder encoder = new BCryptPasswordEncoder();

            User user = new User("teste", "teste@gmail.com", encoder.encode("teste123"), "user");

            userRepository.save(user);
        };
    }
}
