package com.ariel.dev22.e_commerce_backend.domains.product.mapper;

import com.ariel.dev22.e_commerce_backend.domains.product.model.dto.ProductDTO;
import com.ariel.dev22.e_commerce_backend.domains.product.model.dto.ProductDetails;
import com.ariel.dev22.e_commerce_backend.domains.product.model.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getUnitPrice(),
                product.getImageUrl()
        );
    }

    public static ProductDetails toProductDetails(Product product) {
        return new ProductDetails(
                product.getId(),
                product.getName(),
                product.getUnitPrice(),
                product.getImageUrl(),
                product.getDetails()
        );
    }

    public static List<ProductDTO> toProductDTOList(List<Product> products) {
        return products.stream().map(ProductMapper::toProductDTO).collect(Collectors.toList());
    }
}
