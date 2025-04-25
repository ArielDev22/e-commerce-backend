package com.ariel.dev22.e_commerce_backend.domains.address.controller;

import com.ariel.dev22.e_commerce_backend.domains.address.models.AddressInfo;
import com.ariel.dev22.e_commerce_backend.domains.address.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my-account/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PutMapping
    public ResponseEntity<String> editAddress(@AuthenticationPrincipal UserDetails user,
                                              @RequestBody @Valid AddressInfo addressInfo) {
        return ResponseEntity.ok(addressService.editAddress(user.getUsername(), addressInfo));
    }
}
