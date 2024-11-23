package com.ariel.dev22.e_commerce_backend.domains.contact.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String protocol;

    private LocalDate timestamp;

    @Column(nullable = false)
    private String clientName;

    @Email
    @Column(nullable = false)
    private String clientEmail;

    private String whatsapp;

    @Lob
    @Column(nullable = false)
    private String message;
}
