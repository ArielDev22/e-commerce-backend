package com.ariel.dev22.e_commerce_backend.order.models;

import com.ariel.dev22.e_commerce_backend.order.models.enums.OrderStatus;
import com.ariel.dev22.e_commerce_backend.payment.models.Payment;
import com.ariel.dev22.e_commerce_backend.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public void setStatus(Integer status) {
        this.status = OrderStatus.getStatusOf(status);
    }
}
