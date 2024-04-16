package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id);

    List<Product> getAllProducts();
}
