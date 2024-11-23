package com.ariel.dev22.e_commerce_backend.domains.order.models.entities;

import com.ariel.dev22.e_commerce_backend.domains.order.models.key.OrderItemPK;
import com.ariel.dev22.e_commerce_backend.domains.product.model.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class OrderItem {
    @JsonIgnore
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Min(1)
    private Integer quantity;

    public OrderItem(Product product, Order order, String name, BigDecimal price, Integer quantity) {
        id.setProduct(product);
        id.setOrder(order);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @JsonIgnore
    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }
}
