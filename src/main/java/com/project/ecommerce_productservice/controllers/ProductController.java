package com.project.ecommerce_productservice.controllers;

import com.project.ecommerce_productservice.models.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    public List<Product> getAllProducts() {
    }
}
