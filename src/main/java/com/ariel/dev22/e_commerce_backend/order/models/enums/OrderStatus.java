package com.ariel.dev22.e_commerce_backend.order.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private Integer statusValue;

    public static OrderStatus getStatusOf(int value){
        for (OrderStatus status : OrderStatus.values()){
            if (status.getStatusValue() == value) return status;
        }
        return null;
    }
}
