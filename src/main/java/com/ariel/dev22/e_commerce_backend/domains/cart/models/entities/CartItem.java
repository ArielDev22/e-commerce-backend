package com.ariel.dev22.e_commerce_backend.domains.cart.models.entities;

import com.ariel.dev22.e_commerce_backend.domains.cart.models.key.CartItemKey;
import com.ariel.dev22.e_commerce_backend.domains.product.model.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private CartItemKey id = new CartItemKey();

    @NotBlank(message = "O nome do item do carrinho não pode estar em branco")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "O preço unitário do item do carrinho não pode ser nulo")
    @DecimalMin(value = "0.0", message = "O preço unitário do item do carrinho não pode ser negativo")
    @Column(nullable = false)
    private BigDecimal unitPrice;

    @NotNull(message = "A quantidade do item do carrinho não ser nula")
    @Column(nullable = false)
    @Min(1)
    private Integer quantity;

    @NotBlank(message = "O item do carrinho deve ter uma url de imagem")
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
        return unitPrice.multiply(BigDecimal.valueOf(quantity.doubleValue()));
    }
}
