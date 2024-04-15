# Project Step-by-Step Guide

## 1. Setting up the project
1. Create a new project in IntelliJ IDEA with Spring Initializr, Maven, Java 17(Because AWS Bean stock at the time of making this can support JDK 17 beans) and add the following dependencies:
    - Spring Web
    - Spring Boot DevTools
    - Lombok
    - Spring Data JPA
    - H2 Database

2. Name the project `ecommerce_productservice`.

3. Add the project to a new Git repository on GitHub.
    - Create a new branch named `main` and push the initial project code to the repository.
    - Add a `.gitignore` file suitable for Java projects.
    - Add a README.md file with the project name and a brief description.

4. In src/main/java, we will find a package named `com.project.ecommerce_productservice`, which is the root package of the project.
   - In this package, we will find a file named `Ecommerce_ProductServiceApplication.java`, which is the main class of the project.

5. In the `ecommerce_productservice` package, create the following packages:
    - `model`: This package will contain the model classes.
       - > Model classes are created based on the nouns in the problem statement, here the nouns are:
           - `Product` and
           - `Category`
    - `controller`: This package will contain the controller classes.
       - > REST suggests that controllers should be named after entities(the resource they are handling), so the controller classes will be:
         - `ProductController` and 
         - `CategoryController`
    - `repository`: This package will contain the repository classes.
       - > The repository classes will be:
         - `ProductRepository` and
         - `CategoryRepository`
    - `service`: This package will contain the service classes, doing all the business logic, while calling the repository classes, as well as interacting with the DTO classes, Product and Category classes.
       - > The service classes will be:
         - `ProductService` and
         - `CategoryService`
    - `DTO`: This package will contain the DTO classes.
       - > The DTO classes will be:
         - `FakeStoreProductDTOs` and
         - `FakeStoreCategoryDTO`
    - `config`: This package will contain the configuration classes.
       - > The configuration classes will be:
         - `ApplicatonConfigurations`: This class will contain the configurations to make beans and other configurations to be used in the application.
    - `exception`: This package will contain the exception classes.
       - > The exception classes will be:
         - `ResourceNotFoundException` and
         - `GlobalExceptionHandler`

## 2. Designing the APIs
### Model Classes
1. In the `model` package, the following classes are present:
    - `Product`: This class will represent the product entity.
      - > Based on the API documentations and details, the Product class will have the following private attributes for now (APIs taken from [FakeStoreAPI](https://fakestoreapi.com/docs)):
        - `id`: Long (Ultimately we use UUID but for now, Long over int because it is a wrapper class, it can be null, avoiding null pointer exceptions. Also, due to its size, it can store a large number of values (2^64))
        - `name`: String
        - `title`: String
        - `description`: String
        - `price`: Double
        - `category`: Category (enum isn't chosen as enum works when data is static and here, we might want to update the category table without changing the code)
    - `Category`: This class will represent the category entity.
      - > Based on the API documentations and details, the Category class will have the following private attributes for now (APIs taken from [FakeStoreAPI](https://fakestoreapi.com/docs)):
        - `id`: Long
        - `name`: String

2. Use `@Getter` and `@Setter` annotations `public class Product` and `public class Category`. 
   - This is a dependency called Lombok which we added at the initialisation of the project.
   - It makes getters and setters for the private attributes automatically.

### ProductController and CategoryController
3. In the `controller` package, the `ProductController` and `CategoryController` classes are present.
   - `ProductController`: This class will contain the REST endpoints for the product entity.
   - `CategoryController`: This class will contain the REST endpoints for the category entity.
   - > Annotations to be used:
     - `@RestController`: This annotation is used to create RESTful web services using Spring MVC.
       - It tells Spring that this class will serve as a controller for handling HTTP requests and will be the first class to be scanned when the application starts.
     - `@RequestMapping`: This annotation is used to map web requests to specific handler classes and/or handler methods.
       - This tells the controller that where should all the API calls with `/products` and `/categories` in their path be sent.
       - For ProductController, the request mapping will be `/products`. So all the API calls with `/products` in their path will be sent to ProductController.
       - For CategoryController, the request mapping will be `/categories`. So all the API calls with `/categories` in their path will be sent to CategoryController.

### Building APIs in ProductController
4. _Now, let's start by building the first API in ProductController._
   - The first API will be a GET API to get all the products.
   - The API will be:
     - `GET /products`
   - The method will be:
     - `public List<Product> getAllProducts(/products)`
       - The method will return a list of all the products, the details of which are available in the `Product` Model class, that's why the return type is `List<Product>`.
     - The method will return a list of all the products.
   - Since it is a GET request, the method will be annotated with `@GetMapping()`, to tell Spring that this method will handle all the GET requests with `/products` in their path, to get all the products.
     - Since the API Documentation says the path to get all the products is `/products`, we don't need to specify the path in the `@GetMapping()` annotation, as it is already specified in the `@RequestMapping()` annotation in the `ProductController` class.
     - If we put `/products` or just `/` in the `@GetMapping()` annotation, it will be `/products/products` or `/products/`, which is not correct.
   - The method will call the `ProductService` class to get all the products. 
     - For now, let's return an empty list. `return new ArrayList<>();`

5. _Let's make another API in the ProductController to get a product by its id._
   - The API will be:
     - `GET /products/{id}`
       - The `{id}` is a path variable, which means the id will be passed in the path.
       - The id will be a Long value, which will be passed in the path. Eg: `/products/1`, `/products/2`, etc.
       - It has to be inside curly braces `{}`, to tell Spring that it is a path variable.
         - If we write `/products/id`, Spring will consider `id` as a string and not a path variable, expecting a variable `id` to provide the value.
   - The method will be:
     - `public Product getProductById(@PathVariable Long id)`
       - The method will return a product with the given id.
     - For now, let's make a method to return only one product.
       - `public Product getSingeProduct(@PathVariable("id) Long id)`
         - Here, getSingleProduct method is having a parameter `id` which is a path variable, so we have to annotate it with `@PathVariable` to tell Spring that the value of `id` will be passed in the path.
         - Important: In the code:
           - ````java
             @GetMapping("/{id}")
             public Product getSingleProduct(@PathVariable("id") Long id) {
                 return new Product();
             }
             ````
             `Long id` can be of any name, but `@GetMapping("/{id}")` and `@PathVariable("id")` should have the same name. 
     - The method will call the `ProductService` class to get the product by its id.
       - For now, let's return a new Product object. `return new Product();`

6. _Let's now build another APIs in the ProductController to add a new product, update a product, replace a product and delete a product._
   1. API: `POST /products`: This API will add a new product.
       - The method will be:
         - `public Product addNewProduct(@RequestBody Product product)`
           - The product details will be passed in the body of the request. So the method will take a `Product` object as a parameter, annotated with `@RequestBody`, to tell Spring that the product details will be passed in the request.
           - The method will return the added product.
         - The method will call the `ProductService` class to add the product.
           - For now, let's return the product passed in the request body. `return product;`
   2. API: `PATCH /products/{id}`: This API will update the product with the given id.
       - The method will be:
         - `public Product updateProduct(@PathVariable Long id, @RequestBody Product product)`
           - The id will be passed in the path and the product details will be passed in the body of the request.
           - The method will return the updated product.
         - The method will call the `ProductService` class to update the product.
           - For now, let's return the product passed in the request body. `return product;`
   3. API: `PUT /products/{id}`: This API will replace the product with the given id.
       - The method will be:
         - `public Product replaceProduct(@PathVariable Long id, @RequestBody Product product)`
           - The id will be passed in the path and the product details will be passed in the body of the request.
           - The method will return the replaced product.
         - The method will call the `ProductService` class to replace the product.
           - For now, let's return the product passed in the request body. `return product;`
   4. API: `DELETE /products/{id}`: This API will delete the product with the given id.
       - The method will be:
           - `public void deleteProduct(@PathVariable Long id)`
             - The id will be passed in the path.
           - The method will call the `ProductService` class to delete the product.
             - For now, let's return nothing. `return;` 

## 3. Coding the APIs in Service Layer
1. _Let's start by coding the first API in the ProductService class._
   - The first API will be to get all the products.
     - All the business logic will be written in the service classes.
       - The controller will get the request from the client and call the relevant service. When it will get the response from the service, it will give the response back to the client.
       - Since the controller will call the service, the service should be an interface, so that we can have multiple implementations of the service.
         - So that we can implement the same service by calling a third-party API, or by calling our own database, etc.
     - The service layer also usually has a 1:1 mapping with the controller layer, meaning all the services w.r.t. products will be in the ProductService class and all the services w.r.t. categories will be in the CategoryService class.
   - So let's create the `ProductService` interface in the `service` package.
     -  Let's just create `Product getSingleProduct(Long id);` method for now.
   - Now let's create an implementation of this interface. For now let's use FakeStoreAPIs, so we create `FakeStoreProductService` class in the `service` package.
     - The `FakeStoreProductService` class will implement the `ProductService` interface.
     - The `FakeStoreProductService` class will have a method `getSingleProduct(Long id)` which will return a new Product object.
### Calling Third-Party APIs
2. Now we need to make a third-party API call to FakeStoreAPI. 
   - To do so, we need to use a Maven Library/Dependency called `RestTemplate`, which tells Spring that we are going to make a REST API call.
   - If we create a bean of RestTemplate, we can use it in the `FakeStoreProductService` class to make a REST API call as well as in other future classes, as the bean will be available in the application context of the application.
     - Spring's Application context is a common place where all the beans are stored, which can then be used to get the beans and use them in the application.
3. So let's create a bean of RestTemplate in the `ApplicationConfigurations` class in the `config` package.
   - Create a `config` package in the `ecommerce_productservice` package.
   - Create a class `ApplicationConfigurations` in the `config` package.
   - Annotate the method with `@Bean` to tell Spring that this method will return a bean.
   - The method will be:
     ```
       public RestTemplate getRestTemplate() {
           return new RestTemplateBuilder().build();
       }
     ```
     - The method will return a new RestTemplate object.
       - It is not creating a Singleton object, but a new object is acting as a Singleton object, as it is created only once and used throughout the application.
       - You can create multiple beans of RestTemplate, but it is not recommended as it will create multiple objects of RestTemplate, which will consume more memory, and it defeats the purpose of using making a Bean in the first place.
       - The RestTemplateBuilder is a builder class to build the RestTemplate object.
       - The build() method will build the RestTemplate object.
4. Now, use this bean in the `FakeStoreProductService`:
   - Create a private attribute and a constructor in the `FakeStoreProductService` class to inject the RestTemplate bean.
   - ```
     private RestTemplate restTemplate;
         
     @Autowired
     public FakeStoreProductService(RestTemplate restTemplate) {
         this.restTemplate = restTemplate;
     }
     ```
     - The `@Autowired` annotation is used to inject the RestTemplate bean into the `FakeStoreProductService` class.
     - The `@Autowired` annotation tells Spring that the RestTemplate bean will be injected into the `FakeStoreProductService` class.
     - We need to use the `@Autowired` annotation because we are not creating the RestTemplate object using the `new` keyword, but we are injecting the bean created in the `ApplicationConfigurations` class.
     - To use `@Autowired`, we need to put `@Service` annotation on the `FakeStoreProductService` class, to tell Spring that this class is a service class.
       - The `@Service` annotation is used to tell Spring that this class is a service class and it will be scanned by Spring when the application starts.
            
   - To summarize,
     - The `FakeStoreProductService` class is a service class, so it is annotated with `@Service`.
     - We tell Spring that we need an object of RestTemplate in the `FakeStoreProductService` class, in order to make a REST API call.
     - We also tell Spring that we will not create the RestTemplate object using the `new` keyword, but we will inject the bean we created in the `ApplicationConfigurations` class.
     - So we use the `@Autowired` annotation to inject the RestTemplate bean into the `FakeStoreProductService` class.
     - Here the issue arises that Spring doesn't know where to find the RestTemplate bean, so we need to tell Spring where to find the bean.
       - To do so, we need to tell Spring that the `ApplicationConfigurations` class is a configuration class, so that Spring can scan the class and find the bean.
       - So we use the `@Configuration` annotation on the `ApplicationConfigurations` class.
      
5. Now, let's make a REST API call to FakeStoreAPI to get a product by its id.
   - In the `services` package, we have a method `getSingleProduct(Long id)` which will call the `FakeStoreProductService` class to get the product by its id using the FakeStoreAPI through the RestTemplate bean:
        ```
        public Product getSingleProduct(Long id) {
            FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
            return convertToProduct(fakeStoreProductDTO);
        }
        ```
   - The RestTemplate object has a lot of methods already present in its code like `getForObject()`, `postForObject()`, etc.
     - The `getForObject()` method is used to make a GET request to the given URL and return the response in the form of an object of the given class.
     - The first parameter is the URL of the API.
     - The second parameter is the class of the object in which the response will be converted.
       #### DTOs:
       - But the response will be in the form of JSON, so we need to convert the JSON response to a Product object.
       - This is done in DTO classes.
         - DTOs are datatype that is there to only talk externally.
         - The DTO classes will have the same attributes as the API response, but the attributes will be public.
       - So let's create a DTO class `FakeStoreProductDTO` in the `DTO` package.
         - The `FakeStoreProductDTO` class will have the same attributes as the API response.
         - The `FakeStoreProductDTO` class will have the following attributes:
           - `id`: Long
           - `title`: String
           - `price`: Double
           - `description`: String
           - `category`: String
           - `image`: String
         - The `FakeStoreProductDTO` class will have a constructor, getters, and setters.

   - The `getForObject()` method will convert the JSON response to a `FakeStoreProductDTO` object.
        - But the method wants to return a Product object, so we need to convert the `FakeStoreProductDTO` object to a `Product` object.
        - So let's create a method in the `FakeStoreProductService` class to do so:
        - ```
          private Product convertToProduct(FakeStoreProductDTO fakeStoreProductDTO) {
              Product product = new Product();
              product.setId(fakeStoreProductDTO.getId());
              product.setTitle(fakeStoreProductDTO.getTitle());
              product.setPrice(fakeStoreProductDTO.getPrice());
              product.setDescription(fakeStoreProductDTO.getDescription());
              product.setImageUrl(fakeStoreProductDTO.getImage()); 
              product.setCategory(new Category);
              product.setTitle(fakeStoreProductDTO.getCategory());
              return product;
          }
          ```
          - This is then used in the `getSingleProduct()` method to convert the `FakeStoreProductDTO` object to a `Product` object.

6. Now we need to call this service in the `ProductController` class.
   - So let's create a private attribute and a constructor in the `ProductController` class to inject the `ProductService` bean.
   - ```
     private ProductService productService;
     
     @Autowired
     public ProductController(ProductService productService) {
         this.productService = productService;
     }
     ```
     - The `@Autowired` annotation is used to inject the `ProductService` bean into the `ProductController` class.
       - The `@Autowired` annotation tells Spring that the `ProductService` bean will be injected into the `ProductController` class.
       - Why are we able to use `@Autowired` here?
         - Because we have annotated the `ProductService` class or one of its implementations with `@Service`, so Spring knows that the `ProductService` class is a service class and it will be scanned by Spring when the application starts. 
   
7. Change the `getSingleProduct()` method in the `ProductController` class to call the `ProductService` class to get the product by its id.
   - ```
     @GetMapping("/{id}")
     public Product getSingleProduct(@PathVariable("id") Long id) {
         return productService.getSingleProduct(id);
     }
     ```
     - The `getSingleProduct()` method will call the `ProductService` class to get the product by its id.
     - The method will return the product returned by the `ProductService` class.