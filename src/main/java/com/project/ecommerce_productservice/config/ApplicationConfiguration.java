package com.project.ecommerce_productservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  //This class will be used to configure beans
public class ApplicationConfiguration {

    @Bean       //This method will be used to create a bean
    public RestTemplate createRestTemplate() {
        return new RestTemplateBuilder().build();
    }
}
