package com.ariel.dev22.e_commerce_backend.product.service;

import com.ariel.dev22.e_commerce_backend.product.dto.ProductDTO;
import com.ariel.dev22.e_commerce_backend.product.model.Product;
import com.ariel.dev22.e_commerce_backend.product.model.enums.Category;
import com.ariel.dev22.e_commerce_backend.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> listByCategory(String category) {
        return productRepository.findAllByCategory(Category.getCategoryOf(category));
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<ProductDTO> searchByName(String search) {
        List<ProductDTO> productsList = new ArrayList<>();

        for (Product product : productRepository.findByName(search)) {
            productsList.add(new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getUnitPrice(),
                    product.getImageUrl()
            ));
        }
        return productsList;
    }
}
