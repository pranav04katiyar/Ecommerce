package com.project.ecommerce_productservice.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class CommonExceptionHandler {
    public ResponseEntity<String> handleProductNotExistException(ProductNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> handlePermissionDeniedException(PermissionDeniedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<String> handleArithmeticException(ArithmeticException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
