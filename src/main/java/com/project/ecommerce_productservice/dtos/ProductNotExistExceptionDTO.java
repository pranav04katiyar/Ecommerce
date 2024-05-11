package com.project.ecommerce_productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotExistExceptionDTO {
    private String message;
    private String details;
}
