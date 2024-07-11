package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.dtos.FakeStoreProductDTO;
import com.project.ecommerce_productservice.exceptions.PermissionDeniedException;
import com.project.ecommerce_productservice.exceptions.ProductNotExistException;
import com.project.ecommerce_productservice.models.Product;
import com.project.ecommerce_productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private final ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new ProductNotExistException("Product with id " + id + " not found");
        }
        Product product = productOptional.get();
        return product;
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
    public Product addNewProduct(SelfProductService product) throws PermissionDeniedException {
        return productRepository.save(product);
    }

    @Override
    public Product addNewProduct(FakeStoreProductDTO productDto) throws PermissionDeniedException {
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
