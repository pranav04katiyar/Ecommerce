package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService{
    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreCategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Category> getAllCategories() {
        return restTemplate.getForObject("https://fakestoreapi.com/products/categories", List.class);
    }
}
