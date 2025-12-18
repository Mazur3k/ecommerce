package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Page<Category> getAll(int pageNumber, int pageSize,String sortOrder, String sortBy);
    Optional<Category> getById(long id);
    Category save(Category category);
    Optional<Category> deleteById(long id);
    Category update(Category category);
    Optional<Category> findByName(String name);
    Category findById(long id);
}
