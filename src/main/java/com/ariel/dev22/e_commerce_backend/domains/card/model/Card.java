package com.ariel.dev22.e_commerce_backend.domains.card.model;

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

    @Column(nullable = false)
    private BigDecimal creditLimit;

    public Card(String number, String expirationDate, String cvc, BigDecimal creditLimit) {
        this.number = number;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.creditLimit = creditLimit;
    }

    public void debit(BigDecimal amount) {
        BigDecimal newCreditLimit = creditLimit.subtract(amount);

        setCreditLimit(newCreditLimit);
    }
}
