package com.ariel.dev22.e_commerce_backend.domains.product.controller;

import com.ariel.dev22.e_commerce_backend.domains.product.mapper.ProductMapper;
import com.ariel.dev22.e_commerce_backend.domains.product.model.dto.ProductDTO;
import com.ariel.dev22.e_commerce_backend.domains.product.model.dto.ProductDetails;
import com.ariel.dev22.e_commerce_backend.domains.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/of")
    public ResponseEntity<List<ProductDTO>> listByCategory(@RequestParam String category) {
        return ResponseEntity.ok(ProductMapper.toProductDTOList(productService.listByCategory(category)));
    }

    @GetMapping(value = "/details/{productId}")
    public ResponseEntity<ProductDetails> productDetails(@PathVariable Long productId) {
        return ResponseEntity.ok(ProductMapper.toProductDetails(productService.findById(productId)));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductDTO>> searchByName(@RequestParam String search) {
        return ResponseEntity.ok(ProductMapper.toProductDTOList(productService.findProductSearchBar(search)));
    }
}
