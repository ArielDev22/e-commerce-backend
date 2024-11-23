package com.ariel.dev22.e_commerce_backend.domains.card.service;

import com.ariel.dev22.e_commerce_backend.domains.card.exception.CardException;
import com.ariel.dev22.e_commerce_backend.domains.card.model.Card;
import com.ariel.dev22.e_commerce_backend.domains.card.model.CardData;
import com.ariel.dev22.e_commerce_backend.domains.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public Card findCardByNumber(String number) {
        Optional<Card> cardOptional = cardRepository.findByNumber(number);

        return cardOptional.orElseThrow(() -> new CardException("Os dados do cartão de crédito inseridos são inválidos. Verifique as informações e tente novamente."));
    }

    public void updateCard(Card card){
        cardRepository.save(card);
    }

    public boolean isValidCardData(CardData cardData) {
        Card card = findCardByNumber(cardData.number());

        if (!cardData.cvc().equals(card.getCvc())) {
            throw new CardException("Os dados do cartão de crédito inseridos são inválidos. Verifique as informações e tente novamente.");
        }

        if (!cardData.expirationDate().equals(card.getExpirationDate())) {
            throw new CardException("Os dados do cartão de crédito inseridos são inválidos. Verifique as informações e tente novamente.");
        }

        return true;
    }
}
