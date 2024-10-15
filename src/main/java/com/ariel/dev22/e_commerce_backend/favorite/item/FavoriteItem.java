package com.ariel.dev22.e_commerce_backend.favorite.item;

import com.ariel.dev22.e_commerce_backend.favorite.Favorite;
import com.ariel.dev22.e_commerce_backend.product.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private FavoriteItemPK id = new FavoriteItemPK();

    private String name;

    private BigDecimal price;

    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate addedAt;

    public FavoriteItem(Product product, Favorite favorite, String name, BigDecimal price, LocalDate addedAt) {
        id.setProduct(product);
        id.setFavorite(favorite);
        this.name = name;
        this.price = price;
        this.addedAt = addedAt;
        imageUrl = product.getImageUrl();
    }

    @JsonIgnore
    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        id.setProduct(product);
    }

    @JsonIgnore
    public Favorite getFavorite(){
        return id.getFavorite();
    }

    public void setFavorite(Favorite favorite){
        id.setFavorite(favorite);
    }
}
