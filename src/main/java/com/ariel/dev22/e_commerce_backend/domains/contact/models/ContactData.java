package com.ariel.dev22.e_commerce_backend.domains.contact.models;

public record ContactData(String name, String email, String whatsapp, String message) {
    public Contact toModel() {
        return new Contact(null, null, null, name, email, whatsapp, message);
    }
}
