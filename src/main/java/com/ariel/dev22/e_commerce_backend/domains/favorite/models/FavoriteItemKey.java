package com.ariel.dev22.e_commerce_backend.domains.favorite.models;

import com.ariel.dev22.e_commerce_backend.domains.product.model.entity.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
public class FavoriteItemKey {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "favorite_id")
    private Favorite favorite;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteItemKey that = (FavoriteItemKey) o;
        return Objects.equals(product, that.product) && Objects.equals(favorite, that.favorite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, favorite);
    }
}
