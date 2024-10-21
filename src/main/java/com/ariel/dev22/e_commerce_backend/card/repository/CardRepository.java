package com.ariel.dev22.e_commerce_backend.card.repository;

import com.ariel.dev22.e_commerce_backend.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByNumber(String number);
}
