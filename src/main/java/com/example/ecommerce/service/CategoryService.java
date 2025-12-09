package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Page<Category> getAll(int pageNumber, int pageSize,String sortOrder, String sortBy);
    Optional<Category> getById(long id);
    Category save(Category category);
    Optional<Category> deleteById(long id);
    Optional<Category> update(Category category);
    Optional<Category> findByName(String name);
}
