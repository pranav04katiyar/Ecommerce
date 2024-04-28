package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.dtos.FakeStoreProductDTO;
import com.project.ecommerce_productservice.models.Category;
import com.project.ecommerce_productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
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
        FakeStoreProductDTO[] response = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        // Convert the response to a list of Product objects
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDTO dto : response) {
            products.add(convertToProduct(dto));
        }
        return products;
    }

    @Override
    public Product addNewProduct(Product product){
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.postForObject("https://fakestoreapi.com/products", product, FakeStoreProductDTO.class);
        return convertToProduct(fakeStoreProductDTO);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDTO(), FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertToProduct(response);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDTO(), FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PATCH, requestCallback, responseExtractor);
        return convertToProduct(response);
    }

    @Override
    public Product deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        return null;
    }

    @Override
    public Category getCategory(Long id) {
        return null;
    }

    @Override
    public Product getProductByCategory(String category) {
        return null;
    }
}
