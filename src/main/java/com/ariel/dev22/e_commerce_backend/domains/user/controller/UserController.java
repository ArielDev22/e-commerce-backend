package com.ariel.dev22.e_commerce_backend.domains.user.controller;

import com.ariel.dev22.e_commerce_backend.domains.user.mapper.UserMapper;
import com.ariel.dev22.e_commerce_backend.domains.user.model.dto.UpdateUserRequest;
import com.ariel.dev22.e_commerce_backend.domains.user.model.dto.UserData;
import com.ariel.dev22.e_commerce_backend.domains.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/my-account")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@AuthenticationPrincipal UserDetails user, @RequestParam(value = "image") MultipartFile image) {
        return ResponseEntity.ok(userService.uploadProfileImage(user.getUsername(), image));
    }

    @GetMapping
    public ResponseEntity<UserData> myAccountData(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(UserMapper.toUserData(userService.findUserByEmail(user.getUsername())));
    }

    @PutMapping
    public ResponseEntity<String> editUserData(@RequestBody @Valid UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUserData(request));
    }
}
