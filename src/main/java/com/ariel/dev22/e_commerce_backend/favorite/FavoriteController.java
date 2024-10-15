package com.ariel.dev22.e_commerce_backend.favorite;

import com.ariel.dev22.e_commerce_backend.favorite.item.FavoriteItem;
import com.ariel.dev22.e_commerce_backend.favorite.item.FavoriteItemDTO;
import com.ariel.dev22.e_commerce_backend.product.ProductIdDTO;
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
    public ResponseEntity<String> addItem(@RequestBody ProductIdDTO id,
                                          @AuthenticationPrincipal UserDetails userDetails){
        String email = userDetails.getUsername();

        return ResponseEntity.ok(favoriteService.addItem(id.productId(), email));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteItemDTO>> listItems(@AuthenticationPrincipal UserDetails userDetails){
        List<FavoriteItemDTO> items = new ArrayList<>();

        for (FavoriteItem i : favoriteService.listItems(userDetails.getUsername())){
            items.add(new FavoriteItemDTO(i.getProduct().getId(), i.getName(), i.getPrice(), i.getImageUrl(), i.getAddedAt()));
        }

        return ResponseEntity.ok(items);
    }

    @DeleteMapping(value = "/remove/{itemId}")
    ResponseEntity<String> removeItem(@PathVariable Long itemId,
                                      @AuthenticationPrincipal UserDetails userDetails){
        String email = userDetails.getUsername();

        return ResponseEntity.ok(favoriteService.removeItem(itemId, email));
    }
}
