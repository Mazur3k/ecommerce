package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
    Product update(Product product);
    List<Product> findAll();
}
