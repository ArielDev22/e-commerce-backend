package com.ariel.dev22.e_commerce_backend.config;

import com.ariel.dev22.e_commerce_backend.card.model.Card;
import com.ariel.dev22.e_commerce_backend.card.repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class CardConfig {
    @Bean
    public CommandLineRunner createTestCards(CardRepository cardRepository) {
        return args -> {
            Card visa = new Card("4242424242424242", "02/32", "123", BigDecimal.valueOf(5000));
            Card mastercard = new Card("5555555555554444", "02/32", "123", BigDecimal.valueOf(5000));

            cardRepository.saveAll(List.of(visa, mastercard));
        };
    }
}
