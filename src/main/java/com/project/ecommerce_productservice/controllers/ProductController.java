package com.project.ecommerce_productservice.controllers;

import com.project.ecommerce_productservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping()
    public List<Product> getAllProducts() {
        return new ArrayList<>();   //Return an empty list for now
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) {
        return new Product();
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product product) {
        Product p = new Product();
        p.getTitle("Added a new product");
        return p;

    }
}
