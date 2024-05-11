package com.project.ecommerce_productservice.exceptions;

public class ProductNotExistException extends Exception{
    public ProductNotExistException(String message) {
        super(message);
    }
}
