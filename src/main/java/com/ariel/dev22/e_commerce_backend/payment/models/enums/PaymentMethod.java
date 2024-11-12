package com.ariel.dev22.e_commerce_backend.payment.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentMethod {
    CREDIT_CARD("credit_card"),
//    PAYPAL("paypal"),
//    PICPAY("picpay"),
//    PIX("pix"),
//    TICKET("ticket"),
    WALLET_BALANCE("wallet");

    private String value;

    public static PaymentMethod getMethodOf(String method){
        for (PaymentMethod paymentMethod : PaymentMethod.values()){
            if (paymentMethod.getValue().equalsIgnoreCase(method)) return paymentMethod;
        }
        return null;
    }
}
