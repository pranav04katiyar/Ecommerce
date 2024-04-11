package com.project.ecommerce_productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;    //Usually UUID is used
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private Category category;
}
