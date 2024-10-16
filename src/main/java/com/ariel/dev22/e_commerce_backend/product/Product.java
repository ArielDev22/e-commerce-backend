package com.ariel.dev22.e_commerce_backend.product;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String imageUrl;

    private String details;

    public Product(String name, BigDecimal price, String category) {
        this.name = name;
        this.price = price;
        this.category = Category.getCategoryOf(category);
    }
}
