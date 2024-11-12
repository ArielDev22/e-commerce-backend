package com.ariel.dev22.e_commerce_backend.favorite.controller;

import com.ariel.dev22.e_commerce_backend.favorite.dto.FavoriteItemDTO;
import com.ariel.dev22.e_commerce_backend.favorite.models.FavoriteItem;
import com.ariel.dev22.e_commerce_backend.favorite.service.FavoriteService;
import com.ariel.dev22.e_commerce_backend.product.dto.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addProductToFavorites(@RequestBody ProductId id,
                                                        @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(favoriteService.addProductToFavorites(id.productId(), userDetails.getUsername()));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteItemDTO>> listAllItems(@AuthenticationPrincipal UserDetails userDetails) {
        List<FavoriteItemDTO> items = new ArrayList<>();

        for (FavoriteItem i : favoriteService.listAllItems(userDetails.getUsername())) {
            items.add(new FavoriteItemDTO(i.getProduct().getId(), i.getName(), i.getUnitPrice(), i.getImageUrl(), i.getAddedAt()));
        }
        return ResponseEntity.ok(items);
    }

    @DeleteMapping(value = "/remove/{itemId}")
    ResponseEntity<String> removeItemFromFavorites(@PathVariable Long itemId,
                                                   @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(favoriteService.removeItemFromFavorites(itemId, userDetails.getUsername()));
    }
}
