package com.ariel.dev22.e_commerce_backend.domains.payment.service;

import com.ariel.dev22.e_commerce_backend.domains.card.model.Card;
import com.ariel.dev22.e_commerce_backend.domains.card.service.CardService;
import com.ariel.dev22.e_commerce_backend.domains.order.models.entities.Order;
import com.ariel.dev22.e_commerce_backend.domains.order.service.OrderService;
import com.ariel.dev22.e_commerce_backend.domains.payment.exception.PaymentException;
import com.ariel.dev22.e_commerce_backend.domains.payment.models.dto.PaymentCardData;
import com.ariel.dev22.e_commerce_backend.domains.payment.models.entity.Payment;
import com.ariel.dev22.e_commerce_backend.domains.payment.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CardService cardService;
    private final OrderService orderService;

    @Transactional
    public Payment creditCardPayment(PaymentCardData data) {
        // VERIFICAR DADOS DO CARTÃO
        if (cardService.isValidCardData(data.cardData())) {
            // RECUPERAR PEDIDO
            Order order = orderService.listOrders(data.shippingData().email()).getLast();

            // CRIAR O PAGAMENTO
            Payment payment = new Payment();
            Card card = cardService.findCardByNumber(data.cardData().number());

            payment.setOrder(order);

            if (validatePayment(payment, card)) {
                card.debit(order.getTotal());

                cardService.updateCard(card);

                payment.setMoment(Instant.now());
                payment.setMethod(data.method());

                order.setPayment(payment);
                order.setStatus(2);

                // ENVIAR EMAIL


                return paymentRepository.save(payment);
            }
        }
        throw new PaymentException("Pagamento não autorizado");
    }

    private boolean validatePayment(Payment payment, Card card) {
        return card.getCreditLimit().doubleValue() >= payment.getOrder().getTotal().doubleValue();
    }
}
