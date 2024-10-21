package com.ariel.dev22.e_commerce_backend.card.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String expirationDate;
    private String cvc;
    private BigDecimal credit_limit;

    public Card(String number, String expirationDate, String cvc, BigDecimal credit_limit) {
        this.number = number;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.credit_limit = credit_limit;
    }

    public void debit(BigDecimal amount) {
        BigDecimal newCredit_limit = credit_limit.subtract(amount);

        setCredit_limit(newCredit_limit);
    }
}
