package com.ariel.dev22.e_commerce_backend.cart;

import com.ariel.dev22.e_commerce_backend.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total;

    @OneToMany(mappedBy = "id.cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> items;

    @JsonIgnore
    @OneToOne
    @MapsId
    private User user;

    public Cart(User user) {
        total = BigDecimal.ZERO;
        this.user = user;
        items = new HashSet<>();
    }

    public void updateTotal() {
        total = BigDecimal.ZERO;
        for (CartItem item : items) {
            total = total.add(item.subTotal());
        }
    }
}
