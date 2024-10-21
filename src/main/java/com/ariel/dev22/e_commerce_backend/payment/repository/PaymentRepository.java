package com.ariel.dev22.e_commerce_backend.payment.repository;

import com.ariel.dev22.e_commerce_backend.payment.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
