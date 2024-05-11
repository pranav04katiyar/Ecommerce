package com.project.ecommerce_productservice.exceptions;

public class PermissionDeniedException extends Exception{
    public PermissionDeniedException(String message) {
        super(message);
    }
}
