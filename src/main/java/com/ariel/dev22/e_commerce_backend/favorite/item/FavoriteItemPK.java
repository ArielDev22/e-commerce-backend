package com.ariel.dev22.e_commerce_backend.favorite.item;

import com.ariel.dev22.e_commerce_backend.favorite.Favorite;
import com.ariel.dev22.e_commerce_backend.product.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
public class FavoriteItemPK {
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
        FavoriteItemPK that = (FavoriteItemPK) o;
        return Objects.equals(product, that.product) && Objects.equals(favorite, that.favorite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, favorite);
    }
}
