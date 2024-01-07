package com.project.ecommerce_productservice.controllers;

import com.project.ecommerce_productservice.models.Product;
import com.project.ecommerce_productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()           //This method will be serving endpoints that start with /products/
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")        //This method will be serving endpoints that start with /products/{id} where {id} is a path variable (anything in curly braces is a path variable)
    public Product getSingleProduct(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);         //Return the product with the given id (the id is a path variable)
    }

    @PostMapping()      //This method will be serving endpoints that start with /products/ and will be using the POST method (Postman -> Body -> raw -> JSON) (POST method is used to create a new resource)
    public Product addNewProduct(@RequestBody Product product) {
        Product p = new Product();
        p.setTitle("A new Product");
        return p;
    }

    @PatchMapping("/{id}")     //This method will be serving endpoints that start with /products/{id} where {id} is a path variable (anything in curly braces is a path variable) and will be using the PATCH method (Postman -> Body -> raw -> JSON) (PATCH method is used to update a resource)
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new Product();
    }

    @PutMapping("/{id}")       //This method will be serving endpoints that start with /products/{id} where {id} is a path variable (anything in curly braces is a path variable) and will be using the PUT method (Postman -> Body -> raw -> JSON) (PUT method is used to replace a resource)
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new Product();
    }

    @DeleteMapping("/{id}")        //This method will be serving endpoints that start with /products/{id} where {id} is a path variable (anything in curly braces is a path variable
    public void deleteProduct(@PathVariable("id") Long id) {
        //Delete the product with the given id
    }



}
