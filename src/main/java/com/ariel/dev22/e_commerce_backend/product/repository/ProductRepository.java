package com.ariel.dev22.e_commerce_backend.product.repository;

import com.ariel.dev22.e_commerce_backend.product.model.enums.Category;
import com.ariel.dev22.e_commerce_backend.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category categoryOf);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Product> findByName(@Param("search") String search);
}
