package com.serverwarrior.springbootcart.service;

import com.serverwarrior.springbootcart.dto.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    List<Product> products = new ArrayList<>();

    public String addProduct(Product product) {
        products.add(product);
        return "success";
    }

    public List getProducts() {
        return products;
    }

    public Product getProduct(Integer id) {
        return products.stream().filter(product -> product.getId().equals(id)).findAny().get();
    }

    public List getProductsByCategory(String category) {
        return products.stream().filter(product -> product.getCategory()
                .getName().equalsIgnoreCase(category)).collect(Collectors.toList());
    }

    public String updateProduct(Product product) {
        for (Product product1: products) {
            if (product1.getId().equals(product.getId())) {
                product1.setCategory(product.getCategory());
                product1.setDescription(product.getDescription());
                product1.setPrice(product.getPrice());
                product1.setDiscount(product.getDiscount());
                return "Product updated successfully";
            }
        }
        return "Product update failed";
    }

    public String deleteProductById(Integer id) {
        for (Product product1: products) {
            if (product1.getId().equals(id)) {
                Product product = getProduct(product1.getId());
                products.remove(product);
                return "Product deletion successful";
            }
        }
        return "product deletion failed";
    }
}
