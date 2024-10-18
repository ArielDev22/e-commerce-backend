package com.ariel.dev22.e_commerce_backend.favorite;

import com.ariel.dev22.e_commerce_backend.favorite.item.FavoriteItem;
import com.ariel.dev22.e_commerce_backend.product.Product;
import com.ariel.dev22.e_commerce_backend.product.ProductRepository;
import com.ariel.dev22.e_commerce_backend.user.User;
import com.ariel.dev22.e_commerce_backend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
@AllArgsConstructor
public class FavoriteService {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private FavoriteRepository favoriteRepository;

    public String addItem(Long productId, String userEmail) {
        User user = (User) userRepository.findByEmail(userEmail);

        Favorite favorite = user.getFavorite();

        Product product = productRepository.findById(productId).get();

        FavoriteItem favoriteItem = favorite
                .getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(product.getId())).findFirst().orElse(null);

        if (favoriteItem == null) {
            favorite.getItems().add(
                    new FavoriteItem(product, favorite, product.getName(), product.getPrice(), LocalDate.now())
            );

            favoriteRepository.save(favorite);

            return "Item adicionado aos favoritos";
        } else {
            return "O item já está em seus favoritos";
        }
    }

    public String removeItem(Long productId, String userEmail) {
        User user = (User) userRepository.findByEmail(userEmail);

        Favorite favorite = user.getFavorite();

        Product product = productRepository.findById(productId).get();

        favorite.getItems().removeIf(item -> item.getProduct().getId().equals(product.getId()));

        favoriteRepository.save(favorite);

        return "Item removido";
    }

    public Set<FavoriteItem> listItems(String userEmail) {
        User user = (User) userRepository.findByEmail(userEmail);

        return user.getFavorite().getItems();
    }
}
