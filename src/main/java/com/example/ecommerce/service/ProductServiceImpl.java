package com.example.ecommerce.service;

import com.example.ecommerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repositories.CategoryRepository;
import com.example.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${app.images.dir}")
    private String imageDir;

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private FileStorageService fileStorageService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, FileStorageService fileStorageService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.fileStorageService = fileStorageService;
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

    @Transactional
    @Override
    public Product update(Product product) {
        return productRepository
                .findById(product.getId())
                .map(_ -> productRepository.save(product))
                .orElseThrow(() -> new ResourceNotFoundException("product with id " + product.getId() + " not found"));
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

    @Override
    public Page<Product> findProductsByKeyword(String keyword, int pageNumber, int pageSize, String sortOrder, String sortBy) {
        Sort sort = "asc".equals(sortOrder) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        return productRepository.findByNameLikeIgnoreCase(keyword, pageable);
    }

    @Transactional
    @Override
    public Product updateProductImage(long productId, MultipartFile image) throws IOException {
        Optional<Product> productById = productRepository.findById(productId);

        Product product = productById.orElseThrow(() -> new ResourceNotFoundException("product with id " + productId + " not found"));
        String fileName = fileStorageService.uploadFile(imageDir, image);
        product.setImage(fileName);

        return productRepository.save(product);
    }
}
