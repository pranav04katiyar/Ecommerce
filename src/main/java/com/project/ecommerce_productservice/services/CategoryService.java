package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.models.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategories();
}
