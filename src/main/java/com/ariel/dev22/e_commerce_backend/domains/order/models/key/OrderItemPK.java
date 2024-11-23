package com.ariel.dev22.e_commerce_backend.domains.order.models.key;

import com.ariel.dev22.e_commerce_backend.domains.order.models.entities.Order;
import com.ariel.dev22.e_commerce_backend.domains.product.model.entity.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class OrderItemPK {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
