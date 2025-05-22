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
            String title = "Thank You for Contacting ZIARA Clothing";
            String body =
                    "Dear Customer,\n" +
                            "\nThank you for reaching out to ZIARA Clothing! We’ve received your message and will get back to you shortly.\n" +
                    "\nOur team is committed to providing you with the best support and ensuring your inquiries are addressed promptly.\n" +
                    "Thank you for choosing ZIARA Clothing. We look forward to assisting you!\n" +
                    "Best regards,\n" + "The ZIARA Clothing Team";

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
