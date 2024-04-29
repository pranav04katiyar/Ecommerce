package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.dtos.FakeStoreProductDTO;
import com.project.ecommerce_productservice.models.Category;
import com.project.ecommerce_productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id);

    List<Product> getAllProducts();

//    Category getCategory(Long id);
//
//    Product getProductByCategory(String category);

    Product addNewProduct(FakeStoreProductDTO productDTO);

    Product updateProduct(Long id, Product product);

    Product replaceProduct(Long id, Product product);

    boolean deleteProduct(Long id);
}
