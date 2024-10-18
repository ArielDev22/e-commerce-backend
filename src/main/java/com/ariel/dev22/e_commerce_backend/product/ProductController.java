package com.ariel.dev22.e_commerce_backend.product;

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
    public ResponseEntity<List<ProductsCategoryDTO>> listByCategory(@RequestParam String category) {
        List<ProductsCategoryDTO> products = new ArrayList<>();

        for (Product p : productService.listByCategory(category)) {
            products.add(new ProductsCategoryDTO(p.getId(), p.getName(), p.getPrice(), p.getImageUrl()));
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/details/{productId}")
    public ResponseEntity<ProductDetailDTO> productDetail(@PathVariable Long productId) {
        Product p = productService.findById(productId);

        ProductDetailDTO product = new ProductDetailDTO(p.getId(), p.getName(), p.getPrice(), p.getImageUrl(), p.getDetails());

        return ResponseEntity.ok(product);
    }
}
