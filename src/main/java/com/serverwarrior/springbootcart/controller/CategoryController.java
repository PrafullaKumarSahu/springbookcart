package com.serverwarrior.springbootcart.controller;

import com.serverwarrior.springbootcart.dto.Category;
import com.serverwarrior.springbootcart.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategory")
    ResponseEntity<Category> addProduct(@RequestBody Category category) {
        String status = categoryService.addCategory(category);
        log.info("Category added status: {}", status);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
}
