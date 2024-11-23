package com.ariel.dev22.e_commerce_backend.domains.user.repository;

import com.ariel.dev22.e_commerce_backend.domains.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
