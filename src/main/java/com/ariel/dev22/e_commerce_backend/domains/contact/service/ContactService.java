package com.ariel.dev22.e_commerce_backend.domains.contact.service;

import com.ariel.dev22.e_commerce_backend.domains.contact.models.Contact;
import com.ariel.dev22.e_commerce_backend.domains.contact.repository.ContactRepository;
import com.ariel.dev22.e_commerce_backend.domains.email.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ContactService {private ContactRepository contactRepository;
    private EmailService emailService;

    public String toForward(Contact contact) {
        contact.setTimestamp(LocalDate.now());

        contactRepository.save(contact);

        emailService.notifyRequestReceived(contact);

        return "Solicitação enviada com sucesso";
    }
}
