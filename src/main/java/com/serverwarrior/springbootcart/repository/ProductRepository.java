package com.serverwarrior.springbootcart.repository;

import com.serverwarrior.springbootcart.dto.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{'Category.name':?0}")
    List findByCategory(String category);
}
