package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.dtos.FakeStoreProductDTO;
import com.project.ecommerce_productservice.models.Category;
import com.project.ecommerce_productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id);

    List<Product> getAllProducts();

    List<Product> getAllProductsByCategory(String category);

    Product addNewProduct(FakeStoreProductDTO productDTO);

    Product replaceProduct(Long id, Product product);

    Product updateProduct(Long id, Product product);

    boolean deleteProduct(Long id);
}
