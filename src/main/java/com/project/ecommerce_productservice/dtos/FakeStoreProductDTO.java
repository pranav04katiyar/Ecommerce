package com.project.ecommerce_productservice.dtos;

import com.project.ecommerce_productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;
}
