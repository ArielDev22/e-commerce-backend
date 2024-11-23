package com.ariel.dev22.e_commerce_backend.domains.address.models;

import com.ariel.dev22.e_commerce_backend.domains.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String district;

    private String city;

    private String number;

    private String cep;

    @Lob
    private String reference;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;
}
