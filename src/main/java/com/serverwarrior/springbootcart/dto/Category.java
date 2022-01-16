package com.serverwarrior.springbootcart.dto;

import com.mongodb.client.model.Collation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "category")
public class Category {

    @Id
    private Integer id;

    @NotNull(message="category name can not be null!")
    private String name;
    private String description;
    private Integer parent;
}
