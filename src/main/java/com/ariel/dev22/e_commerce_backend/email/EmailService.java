package com.ariel.dev22.e_commerce_backend.email;

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
        }
    }
}
