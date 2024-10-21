package com.ariel.dev22.e_commerce_backend.order.models.pk;

import com.ariel.dev22.e_commerce_backend.order.models.Order;
import com.ariel.dev22.e_commerce_backend.product.model.Product;
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
