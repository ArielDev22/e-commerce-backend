package com.ariel.dev22.e_commerce_backend.domains.payment.repository;

import com.ariel.dev22.e_commerce_backend.domains.payment.models.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
