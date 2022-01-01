package com.serverwarrior.springbootcart.repository;

import com.serverwarrior.springbootcart.dto.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, Integer> {
}