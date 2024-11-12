package com.ariel.dev22.e_commerce_backend.favorite.service;

import com.ariel.dev22.e_commerce_backend.favorite.models.Favorite;
import com.ariel.dev22.e_commerce_backend.favorite.models.FavoriteItem;
import com.ariel.dev22.e_commerce_backend.favorite.repository.FavoriteRepository;
import com.ariel.dev22.e_commerce_backend.product.model.Product;
import com.ariel.dev22.e_commerce_backend.product.service.ProductService;
import com.ariel.dev22.e_commerce_backend.user.model.User;
import com.ariel.dev22.e_commerce_backend.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
@AllArgsConstructor
public class FavoriteService {
    private UserRepository userRepository;
    private ProductService productService;
    private FavoriteRepository favoriteRepository;

    public String addProductToFavorites(Long productId, String userEmail) {
        User user = (User) userRepository.findByEmail(userEmail);

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
        User user = (User) userRepository.findByEmail(userEmail);

        Favorite favorite = user.getFavorite();

        Product product = productService.findById(productId);

        favorite.getItems().removeIf(item -> item.getProduct().getId().equals(product.getId()));

        favoriteRepository.save(favorite);

        return "Item removido";
    }

    public Set<FavoriteItem> listAllItems(String userEmail) {
        User user = (User) userRepository.findByEmail(userEmail);

        return user.getFavorite().getItems();
    }
}
