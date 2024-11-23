package com.ariel.dev22.e_commerce_backend.domains.card.repository;

import com.ariel.dev22.e_commerce_backend.domains.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByNumber(String number);
}
