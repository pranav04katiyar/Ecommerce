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
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        Category category = new Category();
        product.setCategory(category);
        product.setTitle(fakeStoreProductDTO.getCategory());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImageUrl(fakeStoreProductDTO.getImage());
        return product;
    }

    private FakeStoreProductDTO convertProductToFakeStoreProductDTO(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setCategory(product.getCategory().getTitle());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImage(product.getImageUrl());
        return fakeStoreProductDTO;
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
        if(fakeStoreProductDTO != null){
            System.out.println("Product found");
            return convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
        }
        System.out.println("Product not found");
        return null;
    }

    public List<Product> getAllProducts() {
        // Make a REST API call to FakeStoreAPI to get all the products
        FakeStoreProductDTO[] response = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        if(response != null){
            // Convert the response to a list of Product objects
            List<Product> products = new ArrayList<>();
            System.out.println("Products found");
            for (FakeStoreProductDTO dto : response) {
                products.add(convertFakeStoreProductDTOToProduct(dto));
            }
            return products;
        }
        System.out.println("No products found");
        return null;
    }

    @Override
    public Product addNewProduct(FakeStoreProductDTO productDto) {
        FakeStoreProductDTO dto = restTemplate.postForObject("https://fakestoreapi.com/products", productDto, FakeStoreProductDTO.class);
        if(dto != null){
            System.out.println("Product added");
            return convertFakeStoreProductDTOToProduct(dto);
        }
        System.out.println("Product not added");
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDTO(), FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        if(response != null){
            System.out.println("Product replaced");
            return convertFakeStoreProductDTOToProduct(response);
        }
        System.out.println("Product not replaced");
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDTO(), FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PATCH, requestCallback, responseExtractor);
        if(response != null){
            System.out.println("Product replaced");
            return convertFakeStoreProductDTOToProduct(response);
        }
        System.out.println("Product not replaced");
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        FakeStoreProductDTO responseDTO = restTemplate.exchange("https://fakestoreapi.com/products/" + id, HttpMethod.DELETE, null, FakeStoreProductDTO.class).getBody();
        if (responseDTO != null){
            System.out.println("Product deleted");
            return true;
        }
        System.out.println("Product not deleted");
        return false;
    }

//    @Override
//    public Category getCategory(Long id) {
//        return null;
//    }
//
//    @Override
//    public Product getProductByCategory(String category) {
//        return null;
//    }
}
