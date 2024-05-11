package com.project.ecommerce_productservice.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class CommonExceptionHandler {
    public ResponseEntity<String> CommonExceptionHandler(ProductNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> CommonExceptionHandler(CategoryNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> CommonExceptionHandler(PermissionDeniedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
}
