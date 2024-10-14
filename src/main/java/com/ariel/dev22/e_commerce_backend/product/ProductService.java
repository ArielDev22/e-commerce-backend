package com.ariel.dev22.e_commerce_backend.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> listByCategory(String category){
        return productRepository.findAllByCategory(Category.getCategoryOf(category));
    }
}
