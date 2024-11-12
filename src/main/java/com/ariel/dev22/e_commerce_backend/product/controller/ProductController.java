package com.ariel.dev22.e_commerce_backend.product.controller;

import com.ariel.dev22.e_commerce_backend.product.dto.ProductDTO;
import com.ariel.dev22.e_commerce_backend.product.dto.ProductDetails;
import com.ariel.dev22.e_commerce_backend.product.dto.ProductsCategory;
import com.ariel.dev22.e_commerce_backend.product.model.Product;
import com.ariel.dev22.e_commerce_backend.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/of")
    public ResponseEntity<List<ProductsCategory>> listByCategory(@RequestParam String category) {
        List<ProductsCategory> products = new ArrayList<>();

        for (Product p : productService.listByCategory(category)) {
            products.add(new ProductsCategory(p.getId(), p.getName(), p.getUnitPrice(), p.getImageUrl()));
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/details/{productId}")
    public ResponseEntity<ProductDetails> productDetail(@PathVariable Long productId) {
        Product p = productService.findById(productId);

        ProductDetails product = new ProductDetails(p.getId(), p.getName(), p.getUnitPrice(), p.getImageUrl(), p.getDetails());

        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductDTO>> searchByName(@RequestParam String search) {
        return ResponseEntity.ok(productService.searchByName(search));
    }
}
