package com.project.ecommerce_productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTOs {

    private Long id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private String image;
}
