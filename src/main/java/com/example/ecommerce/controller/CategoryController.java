package com.example.ecommerce.controller;

import com.example.ecommerce.constants.AppConstants;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.payload.CategoryDTO;
import com.example.ecommerce.payload.CategoryResponse;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public CategoryController(CategoryService categoryService, ProductService productService, ModelMapper mapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.modelMapper = mapper;
    }

    @GetMapping
    public ResponseEntity<CategoryResponse> getCategories(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) int pageNumber,
                                                          @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE) int pageSize,
                                                          @RequestParam(value = "sortOrder", defaultValue = AppConstants.SORT_ORDER_CATEGORIES) String sortOrder,
                                                          @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_CATEGORIES) String sortBy) {
        Page<Category> pageCategory = categoryService.getAll(pageNumber, pageSize, sortOrder, sortBy);
        List<CategoryDTO> allCategories = pageCategory.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)).toList();

        CategoryResponse categoryResponse = CategoryResponse.builder()
                .content(allCategories)
                .isLastPage(pageCategory.isLast())
                .pageNumber(pageCategory.getNumber())
                .pageSize(pageCategory.getSize())
                .totalPages(pageCategory.getTotalPages())
                .totalElements(pageCategory.getTotalElements())
                .build();

        return ResponseEntity.ok(categoryResponse);
    }

    @PostMapping
    public  ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDto){
        Category savedCategory = categoryService.save(modelMapper.map(categoryDto, Category.class));
        return new ResponseEntity<>(modelMapper.map(savedCategory, CategoryDTO.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable("categoryId") long id){
        return categoryService.deleteById(id)
                .map(deleted -> modelMapper.map(deleted, CategoryDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDto){
        Category category = modelMapper.map(categoryDto, Category.class);
        Category updated = categoryService.update(category);
        return ResponseEntity.ok(modelMapper.map(updated, CategoryDTO.class));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name){
        return categoryService
                .findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
