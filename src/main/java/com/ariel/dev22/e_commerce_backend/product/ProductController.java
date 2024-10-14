package com.ariel.dev22.e_commerce_backend.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/of")
    public ResponseEntity<List<ProductsCategoryDTO>> listByCategory(@RequestParam String category){
        List<ProductsCategoryDTO> products = new ArrayList<>();

        for (Product p : productService.listByCategory(category)){
            products.add(new ProductsCategoryDTO(p.getId(), p.getName(), p.getPrice(), p.getImageUrl()));
        }

        return ResponseEntity.ok(products);
    }
}
