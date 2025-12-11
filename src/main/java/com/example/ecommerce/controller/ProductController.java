package com.example.ecommerce.controller;

import com.example.ecommerce.constants.AppConstants;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.payload.ProductDTO;
import com.example.ecommerce.payload.ProductResponse;
import com.example.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/products")
public class ProductController {

    private ProductService productService;
    private ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping("/category/{categoryId}")
    public ProductDTO addProductToCategory(@RequestBody Product product, @PathVariable long categoryId) {
        return modelMapper.map(productService.addProductToCategory(product, categoryId), ProductDTO.class);
    }

    @GetMapping
    public ResponseEntity<ProductResponse> findAll(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortOrder", defaultValue = AppConstants.SORT_ORDER_PRODUCTS) String sortOrder,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_PRODUCTS) String sortBy
    ){
        Page<Product> allProducts = productService.findAll(pageNumber, pageSize, sortOrder, sortBy);

        List<ProductDTO> listProducts = allProducts.stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();

        ProductResponse productResponse = ProductResponse.builder()
                .content(listProducts)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .totalPages(allProducts.getTotalPages())
                .totalElements(allProducts.getTotalElements())
                .build();

        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
