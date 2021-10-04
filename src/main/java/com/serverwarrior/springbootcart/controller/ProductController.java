package com.serverwarrior.springbootcart.controller;

import com.serverwarrior.springbootcart.dto.Product;

import com.serverwarrior.springbootcart.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping("/addProduct")
    ResponseEntity<Product> addProduct(@RequestBody Product product) {
        String status = productService.addProduct(product);
        logger.info("Product added status: {}", status);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/productList")
    List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable Integer id) {
        return productService.getProduct(id);
    }

    @GetMapping("/productList/{category}")
    List getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    @PutMapping("/product")
    String updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    String deleteProduct(@PathVariable Integer id) {
        return productService.deleteProductById(id);
    }

}
