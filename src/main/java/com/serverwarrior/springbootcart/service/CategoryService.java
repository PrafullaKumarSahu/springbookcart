package com.serverwarrior.springbootcart.service;

import com.serverwarrior.springbootcart.dto.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    List<Category> categoryList = new ArrayList<Category>();
    public String addCategory(Category category) {
        categoryList.add(category);
        return "success";
    }
}
