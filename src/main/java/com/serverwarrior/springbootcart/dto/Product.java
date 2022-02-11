package com.serverwarrior.springbootcart.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    private String id;
    @NotNull(message = "Name should not be null!")
    private String name;
    private String description;
    @Min(0)
    private double price;
    private String currency;
    @Max(100)
    private double discount;
    private String discountDescription;
    @NotNull(message = "Category should not be null!")
    private Category category;
    private List<String> imageURLs;
}
