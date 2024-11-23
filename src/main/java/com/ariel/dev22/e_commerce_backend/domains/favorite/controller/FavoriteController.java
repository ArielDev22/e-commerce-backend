package com.ariel.dev22.e_commerce_backend.domains.favorite.controller;

import com.ariel.dev22.e_commerce_backend.domains.favorite.mapper.FavoriteMapper;
import com.ariel.dev22.e_commerce_backend.domains.favorite.models.FavoriteItemDTO;
import com.ariel.dev22.e_commerce_backend.domains.favorite.service.FavoriteService;
import com.ariel.dev22.e_commerce_backend.domains.product.model.dto.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addProductToFavorites(@RequestBody ProductId id,
                                                        @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(favoriteService.addProductToFavorites(id.productId(), userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteItemDTO>> listAllItems(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(FavoriteMapper.toFavoriteItemDTOList(favoriteService.listAllItems(userDetails.getUsername())));
    }

    @DeleteMapping(value = "/remove/{itemId}")
    ResponseEntity<String> removeItemFromFavorites(@PathVariable Long itemId,
                                                   @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(favoriteService.removeItemFromFavorites(itemId, userDetails.getUsername()));
    }
}
