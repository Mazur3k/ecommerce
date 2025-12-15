package com.example.ecommerce.repositories;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);

    @Query(value = "select * from products", nativeQuery = true)
    Page<Product> findAllProducts(Pageable pageable);
    Page<Product> findByNameLikeIgnoreCase(String keyword, Pageable pageable);
}
