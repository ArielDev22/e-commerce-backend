package com.ariel.dev22.e_commerce_backend.domains.address.repository;

import com.ariel.dev22.e_commerce_backend.domains.address.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
