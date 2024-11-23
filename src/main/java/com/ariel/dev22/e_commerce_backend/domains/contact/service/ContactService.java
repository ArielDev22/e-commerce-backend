package com.ariel.dev22.e_commerce_backend.domains.contact.service;

import com.ariel.dev22.e_commerce_backend.domains.contact.models.Contact;
import com.ariel.dev22.e_commerce_backend.domains.contact.repository.ContactRepository;
import com.ariel.dev22.e_commerce_backend.domains.email.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class ContactService {
    private static final AtomicInteger counter = new AtomicInteger(1);

    private ContactRepository contactRepository;
    private EmailService emailService;

    public String toForward(Contact contact) {
        contact.setTimestamp(LocalDate.now());
        contact.setProtocol(generateProtocol(contact.getTimestamp()));

        contactRepository.save(contact);

        emailService.notifyRequestReceived(contact);

        return "Solicitação enviada com sucesso";
    }

    private String generateProtocol(LocalDate timestamp) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        String sequentialNumber = String.format("%03d", counter.getAndIncrement());
        return format.format(timestamp) + sequentialNumber;
    }
}
