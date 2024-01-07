package com.project.ecommerce_productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter        //This will generate getters for all fields through Lombok dependency at compile time.
@Setter         //This will generate setters for all fields through Lombok dependency at compile time.
public class Product {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image_url;
    private Category category;
}
