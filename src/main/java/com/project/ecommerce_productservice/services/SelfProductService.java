package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.dtos.FakeStoreProductDTO;
import com.project.ecommerce_productservice.exceptions.PermissionDeniedException;
import com.project.ecommerce_productservice.exceptions.ProductNotExistException;
import com.project.ecommerce_productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotExistException {
        return List.of();
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) throws ProductNotExistException {
        return List.of();
    }

    @Override
    public Product addNewProduct(FakeStoreProductDTO productDTO) throws PermissionDeniedException {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws PermissionDeniedException {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws PermissionDeniedException {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) throws PermissionDeniedException {
        return false;
    }
}
