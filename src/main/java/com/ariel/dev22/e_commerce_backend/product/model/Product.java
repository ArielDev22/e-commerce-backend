package com.ariel.dev22.e_commerce_backend.product.model;

import com.ariel.dev22.e_commerce_backend.product.model.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "O nome do produto não pode estar em branco")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "O preço unitário não pode ser nulo")
    @Column(nullable = false)
    @DecimalMin(value = "0.0", message = "O preço unitário do produto não pode ser negativo")
    private BigDecimal unitPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private String imageUrl;

    @NotBlank(message = "Os detalhes não pode estar em branco")
    @Column(nullable = false)
    @Lob
    private String details;

    public Product(String name, BigDecimal unitPrice, String category, String details) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.category = Category.getCategoryOf(category);
        this.details = details;
    }
}
