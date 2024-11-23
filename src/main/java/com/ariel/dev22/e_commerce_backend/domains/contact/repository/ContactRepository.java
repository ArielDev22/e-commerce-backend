package com.ariel.dev22.e_commerce_backend.domains.contact.repository;

import com.ariel.dev22.e_commerce_backend.domains.contact.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
