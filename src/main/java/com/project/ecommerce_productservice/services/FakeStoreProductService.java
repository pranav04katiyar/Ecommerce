package com.project.ecommerce_productservice.services;

import com.project.ecommerce_productservice.dtos.FakeStoreProductDTO;
import com.project.ecommerce_productservice.exceptions.PermissionDeniedException;
import com.project.ecommerce_productservice.exceptions.ProductNotExistException;
import com.project.ecommerce_productservice.models.Category;
import com.project.ecommerce_productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        Category category = new Category();
        product.setCategory(category);
        product.setTitle(fakeStoreProductDTO.getCategory());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImageUrl(fakeStoreProductDTO.getImage());
        return product;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
        if(fakeStoreProductDTO != null){
            System.out.println("Product found");
            return convertFakeStoreProductDTOToProduct(fakeStoreProductDTO);
        }
        if(fakeStoreProductDTO == null){
            throw new ProductNotExistException("Product with id " + id + " not found");
        }
        System.out.println("Product not found");
        return null;
    }

    public List<Product> getAllProducts() throws ProductNotExistException{
        // Make a REST API call to FakeStoreAPI to get all the products
        FakeStoreProductDTO[] response = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        if(response != null){
            // Convert the response to a list of Product objects
            List<Product> products = new ArrayList<>();
            System.out.println("Products found");
            for (FakeStoreProductDTO dto : response) {
                products.add(convertFakeStoreProductDTOToProduct(dto));
            }
            return products;
        }
        if(response == null){
            throw new ProductNotExistException("No products found");
        }
        System.out.println("No products found");
        return null;
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) throws ProductNotExistException {
        FakeStoreProductDTO[] fakeStoreProducts = restTemplate.getForObject("https://fakestoreapi.com/products/category/" + category, FakeStoreProductDTO[].class);
        List<Product> products = new ArrayList<>();
        if (fakeStoreProducts != null){
            for (FakeStoreProductDTO productDto : fakeStoreProducts) {
                products.add(convertFakeStoreProductDTOToProduct(productDto));
            }
            System.out.println("Products found");
            return products;
        }
        if (fakeStoreProducts == null){
            throw new ProductNotExistException("No products found");
        }
        System.out.println("No products found");
        return null;
    }

    @Override
    public Product addNewProduct(SelfProductService product) throws PermissionDeniedException {
        return null;
    }

    @Override
    public Product addNewProduct(FakeStoreProductDTO productDto)  throws PermissionDeniedException {
        FakeStoreProductDTO dto = restTemplate.postForObject("https://fakestoreapi.com/products", productDto, FakeStoreProductDTO.class);
        if(dto != null){
            System.out.println("Product added");
            return convertFakeStoreProductDTOToProduct(dto);
        }
        if(dto == null){
            throw new PermissionDeniedException("Permission denied, not authorized to add product");
        }
        System.out.println("Product not added");
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws PermissionDeniedException{
        RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDTO(), FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        if(response != null){
            System.out.println("Product replaced");
            return convertFakeStoreProductDTOToProduct(response);
        }
        if (response == null){
            throw new PermissionDeniedException("Permission denied, not authorized to replace product");
        }
        System.out.println("Product not replaced");
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws PermissionDeniedException{
          RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDTO(), FakeStoreProductDTO.class);
          ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
          FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/"+ id, HttpMethod.PATCH, requestCallback, responseExtractor).getBody();
          if(response != null){
              System.out.println("Product updated");
              return convertFakeStoreProductDTOToProduct(response);
          }
            if (response == null){
                throw new PermissionDeniedException("Permission denied, not authorized to update product");
            }
          System.out.println("Product not replaced");
          return null;
    }

    @Override
    public boolean deleteProduct(Long id) throws PermissionDeniedException{
        FakeStoreProductDTO responseDTO = restTemplate.exchange("https://fakestoreapi.com/products/" + id, HttpMethod.DELETE, null, FakeStoreProductDTO.class).getBody();
        if (responseDTO != null){
            System.out.println("Product deleted");
            return true;
        }
        if (responseDTO == null){
            throw new PermissionDeniedException("Permission denied, not authorized to delete product");
        }
        System.out.println("Product not deleted");
        return false;
    }

}
