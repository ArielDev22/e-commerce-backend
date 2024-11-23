package com.ariel.dev22.e_commerce_backend.domains.favorite.mapper;

import com.ariel.dev22.e_commerce_backend.domains.favorite.models.FavoriteItem;
import com.ariel.dev22.e_commerce_backend.domains.favorite.models.FavoriteItemDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FavoriteMapper {
    public static FavoriteItemDTO toFavoriteItemDTO(FavoriteItem item) {
        return new FavoriteItemDTO(
                item.getProduct().getId(),
                item.getName(),
                item.getUnitPrice(),
                item.getImageUrl(),
                item.getAddedAt()
        );
    }

    public static List<FavoriteItemDTO> toFavoriteItemDTOList(Set<FavoriteItem> items) {
        return items.stream().map(FavoriteMapper::toFavoriteItemDTO).collect(Collectors.toList());
    }
}
