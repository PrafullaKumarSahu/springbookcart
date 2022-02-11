package com.serverwarrior.springbootcart.service;

import com.serverwarrior.springbootcart.config.ProductConfiguration;
import com.serverwarrior.springbootcart.dto.Product;
import com.serverwarrior.springbootcart.exception.CurrencyNotAvailableException;
import com.serverwarrior.springbootcart.exception.OfferNotValidException;
import com.serverwarrior.springbootcart.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductConfiguration productConfiguration;

    public String addProduct(Product product) {
        if (product.getPrice() == 0 && product.getDiscount() > 0) {
            throw new OfferNotValidException("Discount not available at 0 product price!");
        }
        if (!productConfiguration.getCurrencies().contains(product.getCurrency().toUpperCase())) {
            throw new CurrencyNotAvailableException("Invalid currency! Valid currencies are " + productConfiguration.getCurrencies());
        }
        productRepository.save(product);
        return "success";
    }

    public List getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(String id) {
        return productRepository.findById(id).get();
    }

    public List getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public String updateProduct(Product product) {
        productRepository.save(product);
        return "Product updated successfully.";
    }

    public String deleteProductById(String id) {
        productRepository.deleteById(id);
        return "product deletion successful.";
    }
}
