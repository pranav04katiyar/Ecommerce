package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.dtos.FakeStoreProductDTO;
import com.project.ecommerce_productservice.exceptions.CategoryNotFoundException;
import com.project.ecommerce_productservice.exceptions.PermissionDeniedException;
import com.project.ecommerce_productservice.exceptions.ProductNotExistException;
import com.project.ecommerce_productservice.models.Category;
import com.project.ecommerce_productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id) throws ProductNotExistException;

    List<Product> getAllProducts() throws ProductNotExistException;

    List<Product> getAllProductsByCategory(String category) throws ProductNotExistException;

    Product addNewProduct(FakeStoreProductDTO productDTO) throws PermissionDeniedException;

    Product replaceProduct(Long id, Product product) throws PermissionDeniedException;

    Product updateProduct(Long id, Product product) throws PermissionDeniedException;

    boolean deleteProduct(Long id) throws PermissionDeniedException;
}
