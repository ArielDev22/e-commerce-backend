package com.ariel.dev22.e_commerce_backend.cart.models;

import com.ariel.dev22.e_commerce_backend.cart.models.pk.CartItemPK;
import com.ariel.dev22.e_commerce_backend.product.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity()
@Table(name = "cart_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class CartItem {
    @JsonIgnore
    @EmbeddedId
    private CartItemPK id = new CartItemPK();

    private String name;

    private BigDecimal price;

    private Integer quantity;

    private String imageUrl;

    public CartItem(Product product, Cart cart) {
        id.setProduct(product);
        id.setCart(cart);
    }

    @JsonIgnore
    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    @JsonIgnore
    public Cart getCart() {
        return id.getCart();
    }

    public void setCart(Cart cart) {
        id.setCart(cart);
    }

    public BigDecimal subTotal() {
        return price.multiply(BigDecimal.valueOf(quantity.doubleValue()));
    }
}
