package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.dtos.FakeStoreProductDTOs;
import com.project.ecommerce_productservice.dtos.FakeStoreProductDTOs;
import com.project.ecommerce_productservice.models.Category;
import com.project.ecommerce_productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service            //This class will be used as a service class and will be used to create beans
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;          //RestTemplate is used to make HTTP calls to other services

    @Autowired          //Constructor injection of RestTemplate (Spring will automatically inject the RestTemplate bean)
    public FakeStoreProductService(RestTemplate restTemplate) {             //Constructor injection of RestTemplate (Spring will automatically inject the RestTemplate bean)
        this.restTemplate = restTemplate;
    }

    private Product convertToProduct(FakeStoreProductDTOs FakeStoreProduct) {      //This method will be used to convert the DTO to a Product object
        Product product = new Product();                //Create a new Product object
        product.setId(FakeStoreProduct.getId());        //Set the id of the product to the id of the FakeStoreProduct
        product.setTitle(FakeStoreProduct.getTitle());    //Set the title of the product to the title of the FakeStoreProduct
        product.setDescription(FakeStoreProduct.getDescription());      //Set the description of the product to the description of the FakeStoreProduct
        product.setCategory(new Category());        //Create a new Category object and set it as the category of the product
        product.getCategory().setName(FakeStoreProduct.getCategory());      //Set the name of the category of the product to the category of the FakeStoreProduct
        product.setPrice(FakeStoreProduct.getPrice());      //Set the price of the product to the price of the FakeStoreProduct
        product.setImage_url(FakeStoreProduct.getImage());      //Set the image_url of the product to the image of the FakeStoreProduct

        return product;         //Return the product
    }
    @Override
    public Product getSingleProduct(Long id) {      //This method will be used to get a single product from the FakeStore API
        FakeStoreProductDTOs productDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTOs.class
        );

        return convertToProduct(productDto);
    }
}
