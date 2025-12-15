package com.example.ecommerce.service;

import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repositories.CategoryRepository;
import com.example.ecommerce.repositories.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product with id " + id + " not found"));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(int pageNumer, int pageSize, String sortOrder, String sortBy) {
        Sort sort = "asc".equals(sortOrder) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumer, pageSize, sort);
        return productRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Product addCategoryToProduct(long productId, long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category with id " + categoryId + " not found"));
        Product prodById = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product with id " + productId + " not found"));

        prodById.setCategory(category);
        return productRepository.save(prodById);
    }

    @Transactional
    @Override
    public Page<Product> findProductsByCategory(String categoryName, int pageNumber, int pageSize, String sortOrder, String sortBy) {
        Category categoryRep = categoryRepository.findByName(categoryName).orElseThrow(() -> new ResourceNotFoundException("category with name " + categoryName + " not found"));
        Sort sort = "asc".equals(sortOrder) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNumber,pageSize, sort);

        return new PageImpl<>(categoryRep.getProducts(), page, pageSize);
    }
}
