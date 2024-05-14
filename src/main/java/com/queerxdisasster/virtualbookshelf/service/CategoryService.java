package com.queerxdisasster.virtualbookshelf.service;

import com.queerxdisasster.virtualbookshelf.entity.Category;
import com.queerxdisasster.virtualbookshelf.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}