package com.ariel.dev22.e_commerce_backend.domains.address.service;

import com.ariel.dev22.e_commerce_backend.domains.address.models.Address;
import com.ariel.dev22.e_commerce_backend.domains.address.models.AddressInfo;
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

    public String editAddress(String userEmail, AddressInfo info) {
        User user = userService.findUserByEmail(userEmail);

        Address address = addressRepository.findByUser(user);

        address.setStreet(info.street());
        address.setDistrict(info.district());
        address.setCity(info.city());
        address.setNumber(info.number());
        address.setCep(info.cep());
        address.setReference(info.reference());

        addressRepository.save(address);

        return "Endereço atualizado";
    }

    public Address getUserAddress(String userEmail) {
        User user = userService.findUserByEmail(userEmail);

        return addressRepository.findByUser(user);
    }
}
