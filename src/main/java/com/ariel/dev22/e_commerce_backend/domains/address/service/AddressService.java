package com.ariel.dev22.e_commerce_backend.domains.address.service;

import com.ariel.dev22.e_commerce_backend.domains.address.models.Address;
import com.ariel.dev22.e_commerce_backend.domains.address.repository.AddressRepository;
import com.ariel.dev22.e_commerce_backend.domains.user.model.User;
import com.ariel.dev22.e_commerce_backend.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserService userService;

    public Address editAddress(String userEmail, Address address) {
        User user = userService.findUserByEmail(userEmail);

        user.setAddress(address);

        return addressRepository.save(address);
    }

    public Address getUserAddress(String userEmail) {
        User user = userService.findUserByEmail(userEmail);

        return user.getAddress();
    }
}
