package com.project.ecommerce_productservice.models;

import lombok.Getter;

@Getter
public class Product {
    private Long id;    //Usually UUID is used
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private Category category;
}
