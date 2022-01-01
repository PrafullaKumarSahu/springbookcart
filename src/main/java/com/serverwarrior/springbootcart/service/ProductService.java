package com.serverwarrior.springbootcart.service;

import com.serverwarrior.springbootcart.dto.Product;
import com.serverwarrior.springbootcart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String addProduct(Product product) {
        productRepository.save(product);
        return "success";
    }

    public List getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Integer id) {
        return productRepository.findById(id).get();
    }

    public List getProductsByCategory(String category) {
        return productRepository.findByCategory();
    }

    public String updateProduct(Product product) {
        productRepository.save(product);
        return "Product updated successfully.";
    }

    public String deleteProductById(Integer id) {
        productRepository.deleteById(id);
        return "product deletion successful.";
    }
}
