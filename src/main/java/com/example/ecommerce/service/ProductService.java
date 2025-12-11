package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
    Product update(Product product);
    Page<Product> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy);
    Product addProductToCategory(Product product, long categoryId);
}
