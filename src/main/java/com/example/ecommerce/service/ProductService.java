package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
    Product update(Product product);
    Page<Product> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy);
    Product addCategoryToProduct(long productId, long categoryId);
    Page<Product> findProductsByCategory(String categoryName, int pageNumber, int pageSize, String sortOrder, String sortBy);
}
