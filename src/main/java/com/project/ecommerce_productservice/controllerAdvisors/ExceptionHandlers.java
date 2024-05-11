package com.project.ecommerce_productservice.controllerAdvisors;

import com.project.ecommerce_productservice.dtos.ArithmeticExceptionDTO;
import com.project.ecommerce_productservice.dtos.CategoryNotFoundExceptionDTO;
import com.project.ecommerce_productservice.dtos.PermissionDeniedDTO;
import com.project.ecommerce_productservice.dtos.ProductNotExistExceptionDTO;
import com.project.ecommerce_productservice.exceptions.CategoryNotFoundException;
import com.project.ecommerce_productservice.exceptions.PermissionDeniedException;
import com.project.ecommerce_productservice.exceptions.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDTO> handleArithmeticException() {
        ArithmeticExceptionDTO arithmeticExceptionDTO = new ArithmeticExceptionDTO();
        arithmeticExceptionDTO.setMessage("Arithmetic exception occurred");
        return new ResponseEntity<>(arithmeticExceptionDTO, HttpStatus.OK);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBoundsException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ProductNotExistExceptionDTO> handleProductNotExistException(ProductNotExistException exception) {
        ProductNotExistExceptionDTO productNotExistExceptionDTO = new ProductNotExistExceptionDTO();
        productNotExistExceptionDTO.setMessage(exception.getMessage());
        return new ResponseEntity<>(productNotExistExceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<CategoryNotFoundExceptionDTO> handleCategoryNotFoundException(CategoryNotFoundException exception) {
        CategoryNotFoundExceptionDTO categoryNotFoundDTO = new CategoryNotFoundExceptionDTO();
        categoryNotFoundDTO.setMessage(exception.getMessage());
        return new ResponseEntity<>(categoryNotFoundDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<PermissionDeniedDTO> handlePermissionDeniedException(PermissionDeniedException exception){
        PermissionDeniedDTO permissionDeniedDTO = new PermissionDeniedDTO();
        permissionDeniedDTO.setMessage(exception.getMessage());
        return new ResponseEntity<>(permissionDeniedDTO, HttpStatus.FORBIDDEN);
    }
}
