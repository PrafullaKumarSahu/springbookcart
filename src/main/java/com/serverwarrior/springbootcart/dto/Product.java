package com.serverwarrior.springbootcart.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "product")
public class Product {

    @Id
    private Integer id;
    private String name;
    private String description;
    private double price;
    private String currency;
    private double discount;
    private String discountDescription;
    private Category category;
    private List<String> imageURLs;
}
