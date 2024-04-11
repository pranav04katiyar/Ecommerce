# Project Step-by-Step Guide

## 1. Setting up the project
1. Create a new project in IntelliJ IDEA with Spring Initializr, Maven, Java 17(Because current AWS can support JDK 17 beans) and add the following dependencies:
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

4. In src/main/java, create a new package named `com.project.ecommerce_productservice`.

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
4. Now, let's start by building the first API in ProductController.
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
5. Let's make another API in the ProductController to get a product by its id.
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
6. Let's now build another API in the ProductController to add a new product.
   - The API will be:
     - `POST /products`
   - The method will be:
     - `public Product addNewProduct(@RequestBody Product product)`
       - The method will add a new product.
       - The product details will be passed in the body of the request. So the method will take a `Product` object as a parameter, annotated with `@RequestBody`, to tell Spring that the product details will be passed in the request.
       - The method will return the added product.
     - The method will call the `ProductService` class to add the product.
       - For now, let's return the product passed in the request body. `return product;`