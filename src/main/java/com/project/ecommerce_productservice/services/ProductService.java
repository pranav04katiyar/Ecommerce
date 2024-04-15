package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.models.Product;

public interface ProductService {

    Product getSingleProduct(Long id);

    Product addNewProduct(Product product);
}
