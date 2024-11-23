package com.ariel.dev22.e_commerce_backend.domains.favorite.service;

import com.ariel.dev22.e_commerce_backend.domains.favorite.models.Favorite;
import com.ariel.dev22.e_commerce_backend.domains.favorite.models.FavoriteItem;
import com.ariel.dev22.e_commerce_backend.domains.favorite.repository.FavoriteRepository;
import com.ariel.dev22.e_commerce_backend.domains.product.model.entity.Product;
import com.ariel.dev22.e_commerce_backend.domains.product.service.ProductService;
import com.ariel.dev22.e_commerce_backend.domains.user.model.User;
import com.ariel.dev22.e_commerce_backend.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final UserService userService;
    private final ProductService productService;
    private final FavoriteRepository favoriteRepository;

    public String addProductToFavorites(Long productId, String userEmail) {
        User user = userService.findUserByEmail(userEmail);

        Favorite favorite = user.getFavorite();

        Product product = productService.findById(productId);

        FavoriteItem favoriteItem = favorite
                .getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(product.getId())).findFirst().orElse(null);

        if (favoriteItem == null) {
            favorite.getItems().add(
                    new FavoriteItem(product, favorite, product.getName(), product.getUnitPrice(), LocalDate.now())
            );

            favoriteRepository.save(favorite);

            return "Item adicionado aos favoritos";
        } else {
            return "O item já está em seus favoritos";
        }
    }

    public String removeItemFromFavorites(Long productId, String userEmail) {
        User user = userService.findUserByEmail(userEmail);

        Favorite favorite = user.getFavorite();

        Product product = productService.findById(productId);

        favorite.getItems().removeIf(item -> item.getProduct().getId().equals(product.getId()));

        favoriteRepository.save(favorite);

        return "Item removido";
    }

    public Set<FavoriteItem> listAllItems(String userEmail) {
        User user = userService.findUserByEmail(userEmail);

        return user.getFavorite().getItems();
    }
}
