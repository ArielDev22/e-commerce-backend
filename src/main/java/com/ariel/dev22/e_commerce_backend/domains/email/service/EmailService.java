package com.ariel.dev22.e_commerce_backend.domains.email.service;

import com.ariel.dev22.e_commerce_backend.domains.contact.models.Contact;
import com.ariel.dev22.e_commerce_backend.domains.email.exception.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {
    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender mailSender;

    public void confirmationEmailGeneration(String receiver) {
        try {
            String title = "Confirme seu email";
            String token = UUID.randomUUID().toString();

            String baseUrl = "https://e-commerce.com/confirm-email?token=" + token;

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(sender);
            message.setTo(receiver);
            message.setSubject(title);
            message.setText(baseUrl);

            mailSender.send(message);
        } catch (MailException e) {
            System.out.println(e.getMessage());
            throw new EmailException("Erro ao enviar o email de confirmação.");
        }
    }

    public void notifyRequestReceived(Contact contact) {
        try {
            String title = "Confirmação de Recebimento da Sua Solicitação";
            String body =
                    "Prezado(a) " + contact.getClientName() +
                            "\nRecebemos sua solicitação e ela foi registrada em nosso sistema sob o número de protocolo: " + contact.getProtocol();
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(sender);
            message.setTo(contact.getClientEmail());
            message.setSubject(title);
            message.setText(body);

            mailSender.send(message);
        } catch (MailException e) {
            System.out.println(e.getMessage());
            throw new EmailException("Erro ao enviar o email.");
        }
    }
}
