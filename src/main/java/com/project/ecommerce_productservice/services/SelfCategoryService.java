package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.exceptions.CategoryNotFoundException;
import com.project.ecommerce_productservice.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService{
    @Override
    public List<Category> getAllCategories() throws CategoryNotFoundException {
        return List.of();
    }
}
