package com.ariel.dev22.e_commerce_backend.domains.product.service;

import com.ariel.dev22.e_commerce_backend.domains.product.model.entity.Product;
import com.ariel.dev22.e_commerce_backend.domains.product.model.enums.Category;
import com.ariel.dev22.e_commerce_backend.domains.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> listByCategory(String category) {
        return productRepository.findAllByCategory(Category.getCategoryOf(category));
    }

    public Product findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        return optionalProduct.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado. id= " + id));
    }

    public List<Product> findProductSearchBar(String search) {
        return productRepository.findByName(search);
    }
}
