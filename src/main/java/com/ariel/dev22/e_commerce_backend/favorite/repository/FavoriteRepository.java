package com.ariel.dev22.e_commerce_backend.favorite.repository;

import com.ariel.dev22.e_commerce_backend.favorite.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
