package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private List<Category> categories = new ArrayList<>();

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> getAll(int pageNumber, int pageSize,String sortOrder, String sortBy) {
        Sort sortWithOrder = "asc".equalsIgnoreCase(sortOrder)? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable categoryPage = PageRequest.of(pageNumber, pageSize, sortWithOrder);
        return categoryRepository.findAll(categoryPage);
    }

    @Override
    public Optional<Category> getById(long id) {
        return categoryRepository.findById(id);
    }

    @Transactional
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Optional<Category> deleteById(long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        categoryRepository.deleteById(id);
        return byId;
    }

    @Transactional
    @Override
    public Optional<Category> update(Category category) {
//        Category oldCategory = categories.stream().filter(cat -> cat.getCategoryId() == category.getCategoryId()).findAny()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        oldCategory.setCategoryId(category.getCategoryId());
//        oldCategory.setCategoryName(category.getCategoryName());

        Optional<Category> toBeUpdatedCategory = categoryRepository.findById(category.getId());
        Optional<Category> updatedCategory = toBeUpdatedCategory.map(c -> {
            c.setId(category.getId());
            c.setName(category.getName());
            return c;
        });

        updatedCategory.ifPresent(updatedCat -> categoryRepository.save(updatedCat));

        return updatedCategory;
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
