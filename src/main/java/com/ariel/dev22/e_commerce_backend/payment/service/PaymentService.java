package com.ariel.dev22.e_commerce_backend.payment.service;

import com.ariel.dev22.e_commerce_backend.card.exception.CardException;
import com.ariel.dev22.e_commerce_backend.card.model.Card;
import com.ariel.dev22.e_commerce_backend.card.repository.CardRepository;
import com.ariel.dev22.e_commerce_backend.order.models.Order;
import com.ariel.dev22.e_commerce_backend.order.service.OrderService;
import com.ariel.dev22.e_commerce_backend.payment.dto.CardData;
import com.ariel.dev22.e_commerce_backend.payment.dto.PaymentCardData;
import com.ariel.dev22.e_commerce_backend.payment.exception.PaymentException;
import com.ariel.dev22.e_commerce_backend.payment.models.Payment;
import com.ariel.dev22.e_commerce_backend.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class PaymentService {
    private PaymentRepository paymentRepository;
    private CardRepository cardRepository;
    private OrderService orderService;

    public Payment creditCardPayment(PaymentCardData data) {
        // VERIFICAR DADOS DO CARTÃO
        Card card = cardRepository.findByNumber(data.cardData().number());
        validateCardData(card, data.cardData());

        // RECUPERAR PEDIDO
        Order order = orderService.listOrders(data.shippingData().email()).getLast();

        // CRIAR O PAGAMENTO
        Payment payment = new Payment();

        payment.setOrder(order);

        // VALIDAR O PAGAMENTO
        if (validatePayment(payment, card)) {
            // DEBITAR DO LIMITE
            card.debit(order.getTotal());

            cardRepository.save(card);

            // SALVAR O PAGAMENTO
            payment.setMoment(Instant.now());
            payment.setMethod(data.method());

            order.setPayment(payment);
            order.setStatus(2);

            // ENVIAR EMAIL


            return paymentRepository.save(payment);
        }
        throw new PaymentException("Pagamento não autorizado");
    }

    private boolean validatePayment(Payment payment, Card card) {
        return card.getCredit_limit().doubleValue() >= payment.getOrder().getTotal().doubleValue();
    }

    private void validateCardData(Card card, CardData cardData) {
        if (card == null) {
            throw new CardException("Os dados do cartão de crédito inseridos são inválidos.\nVerifique as informações e tente novamente.");
        }

        if (!card.getExpirationDate().equals(cardData.expirationDate()) && !card.getCvc().equals(cardData.cvc())) {
            throw new CardException("Os dados do cartão de crédito inseridos são inválidos.\nVerifique as informações e tente novamente.");
        }
    }
}
