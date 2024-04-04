package com.project.ecommerce_productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTOs {

    private Long id;        //int(10^9 or 2^16) is not enough to store the id of the product, so we use Long (10^18 or 2^64), or BigInteger, or UUID
    private String title;
    private String description;
    private String category;
    private Double price;
    private String image;
}
