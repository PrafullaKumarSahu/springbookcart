package com.serverwarrior.springbootcart.service;

import com.serverwarrior.springbootcart.dto.Category;
import com.serverwarrior.springbootcart.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "success";
    }
}
