package com.ariel.dev22.e_commerce_backend.domains.payment.models.entity;

import com.ariel.dev22.e_commerce_backend.domains.order.models.entities.Order;
import com.ariel.dev22.e_commerce_backend.domains.payment.models.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant moment;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @JsonIgnore
    @OneToOne
    @MapsId
    private Order order;

    public void setMethod(String method) {
        this.method = PaymentMethod.getMethodOf(method);
    }
}
