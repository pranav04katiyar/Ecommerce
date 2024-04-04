package com.project.ecommerce_productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter        //This will generate getters for all fields through Lombok dependency at compile time.
@Setter         //This will generate setters for all fields through Lombok dependency at compile time.
public class Product {
    private Long id;     //int(10^9 or 2^16) is not enough to store the id of the product, so we use Long (10^18 or 2^64), or BigInteger, or UUID
    private String title;
    private String description;
    private Double price;
    private String image_url;
    private Category category;   //We don't use enum or String because we can have multiple categories in the future and we don't want to change the code every time we add a new category
}
// enum should be used when we have a fixed set of values that will never change, like days of the week, months of the year, etc.