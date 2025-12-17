package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    Product findById(Long id);
    Product save(Product product);
    void delete(Long id);
    Product update(Product product);
    Page<Product> findAll(int pageNumber, int pageSize, String sortOrder, String sortBy);
    Product addCategoryToProduct(long productId, long categoryId);
    Page<Product> findProductsByCategory(String categoryName, int pageNumber, int pageSize, String sortOrder, String sortBy);
    Page<Product> findProductsByKeyword(String keyword, int pageNumber, int pageSize, String sortOrder, String sortBy);
    Product updateProductImage(long productId, MultipartFile imageUrl) throws IOException;
}
