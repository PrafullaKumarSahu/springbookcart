package com.serverwarrior.springbootcart.dto;

import com.mongodb.client.model.Collation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "category")
public class Category {

    @Id
    private Integer id;
    private String name;
    private String description;
    private Integer parent;
}
