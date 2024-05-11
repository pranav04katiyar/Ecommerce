package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.dtos.FakeStoreCategoryDTO;
import com.project.ecommerce_productservice.dtos.FakeStoreProductDTO;
import com.project.ecommerce_productservice.exceptions.CategoryNotFoundException;
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

    public Category convertFakeStoreCategoryDTOToCategory(FakeStoreCategoryDTO fakeStoreCategoryDTO) {
        Category category = new Category();
        category.setId(fakeStoreCategoryDTO.getId());
        category.setTitle(fakeStoreCategoryDTO.getTitle());
        return category;
    }

    @Override
    public List<Category> getAllCategories() throws CategoryNotFoundException {

        FakeStoreCategoryDTO[] response = restTemplate.getForObject("https://fakestoreapi.com/products/categories", FakeStoreCategoryDTO[].class);

        if(response != null){
            List<Category> categories = new ArrayList<>();
            for(FakeStoreCategoryDTO fakeStoreCategoryDTO : response){
                categories.add(convertFakeStoreCategoryDTOToCategory(fakeStoreCategoryDTO));
            }
            return categories;
        }
        if(response == null){
            throw new CategoryNotFoundException("Categories not found");
        }
        System.out.println("Categories not found");
        return null;
    }
}
