package com.ariel.dev22.e_commerce_backend.payment.controller;

import com.ariel.dev22.e_commerce_backend.payment.dto.PaymentCardData;
import com.ariel.dev22.e_commerce_backend.payment.models.Payment;
import com.ariel.dev22.e_commerce_backend.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/credit-card")
    public ResponseEntity<Payment> payWithCreditCard(@RequestBody PaymentCardData data) {
        return ResponseEntity.ok(paymentService.creditCardPayment(data));
    }
}
