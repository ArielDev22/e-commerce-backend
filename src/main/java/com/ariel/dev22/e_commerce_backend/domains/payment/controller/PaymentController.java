package com.ariel.dev22.e_commerce_backend.domains.payment.controller;

import com.ariel.dev22.e_commerce_backend.domains.payment.models.dto.PaymentCardData;
import com.ariel.dev22.e_commerce_backend.domains.payment.models.entity.Payment;
import com.ariel.dev22.e_commerce_backend.domains.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<Payment> payWithCreditCard(@AuthenticationPrincipal UserDetails user,
                                                     @RequestBody PaymentCardData data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.creditCardPayment(data, user.getUsername()));
    }
}
