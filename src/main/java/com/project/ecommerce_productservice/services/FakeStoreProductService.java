package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.models.Product;

public class FakeStoreProductService implements ProductService{

    @Override
    public Product getSingleProduct(Long id) {
        return null;
    }
}
