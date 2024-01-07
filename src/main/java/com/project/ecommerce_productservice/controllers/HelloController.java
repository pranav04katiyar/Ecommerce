package com.project.ecommerce_productservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController //This class will be serving REST API endpoints
@RequestMapping("/hello")  //This class will have multiple methods that will be serving endpoints that start with /hello
public class HelloController {

    @GetMapping("/")  //This method will be serving endpoints that start with /hello/
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/{name}")  //This method will be serving endpoints that start with /hello/{name} where {name} is a path variable (anything in curly braces is a path variable)
    public String helloWorld(@PathVariable("name") String name) {
        return "Hello " + name + "!";
    }
}
