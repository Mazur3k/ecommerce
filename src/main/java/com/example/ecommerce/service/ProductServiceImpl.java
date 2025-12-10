package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product with id " + id + "not found"));
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
}
