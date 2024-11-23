package com.ariel.dev22.e_commerce_backend.domains.favorite.models;

import com.ariel.dev22.e_commerce_backend.domains.product.model.entity.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "favorite_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class FavoriteItem {
    @JsonIgnore
    @EmbeddedId
    private FavoriteItemKey id = new FavoriteItemKey();

    @NotBlank(message = "O nome do item dos favoritos não pode estar em branco")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "O preço unitário do item dos favoritos não pode ser nulo")
    @DecimalMin(value = "0.0", message = "O preço unitário item dos favoritos não pode ser negativo")
    @Column(nullable = false)
    private BigDecimal unitPrice;

    @NotBlank(message = "O item dos favoritos deve ter uma url de imagem")
    @Column(nullable = false)
    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate addedAt;

    public FavoriteItem(Product product, Favorite favorite, String name, BigDecimal unitPrice, LocalDate addedAt) {
        id.setProduct(product);
        id.setFavorite(favorite);
        this.name = name;
        this.unitPrice = unitPrice;
        this.addedAt = addedAt;
        imageUrl = product.getImageUrl();
    }

    @JsonIgnore
    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    @JsonIgnore
    public Favorite getFavorite() {
        return id.getFavorite();
    }

    public void setFavorite(Favorite favorite) {
        id.setFavorite(favorite);
    }
}
