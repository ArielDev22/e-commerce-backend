package com.ariel.dev22.e_commerce_backend.domains.address.controller;

import com.ariel.dev22.e_commerce_backend.domains.address.models.Address;
import com.ariel.dev22.e_commerce_backend.domains.address.models.AddressInfo;
import com.ariel.dev22.e_commerce_backend.domains.address.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my-account/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PutMapping
    public ResponseEntity<Address> editAddress(@AuthenticationPrincipal UserDetails user,
                                               @RequestBody @Valid AddressInfo addressInfo) {
        return ResponseEntity.ok(addressService.editAddress(user.getUsername(), addressInfo.toModel()));
    }

    @GetMapping
    public ResponseEntity<Address> getUserAddress(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(addressService.getUserAddress(user.getUsername()));
    }
}
