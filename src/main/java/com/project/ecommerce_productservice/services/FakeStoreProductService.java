package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.dtos.FakeStoreProductDTO;
import com.project.ecommerce_productservice.models.Category;
import com.project.ecommerce_productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    public Product convertToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setCategory(new Category());
        product.setTitle(fakeStoreProductDTO.getCategory());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImageUrl(fakeStoreProductDTO.getImage());
        return product;
    }
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
        return convertToProduct(fakeStoreProductDTO);
    }

    public List<Product> getAllProducts() {
        // Make a REST API call to FakeStoreAPI to get all the products
        List<FakeStoreProductDTO> response = restTemplate.getForObject("https://fakestoreapi.com/products", List<FakeStoreProductDTO>.class);

        // Convert the response to a list of Product objects
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDTO dto : response) {
            products.add(convertToProduct(dto));
        }
        return products;
    }
}
