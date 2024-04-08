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

6. In the `model` package, the following classes are present:
    - `Product`: This class will represent the product entity.
      - > Based on the API documentations and details, the Product class will have the following attributes for now (APIs taken from [FakeStoreAPI](https://fakestoreapi.com/docs)):
        - `id`: Long (Ultimately we use UUID but for now, Long over int because it is a wrapper class, it can be null, avoiding null pointer exceptions. Also, due to its size, it can store a large number of values (2^64))
        - `name`: String
        - `description`: String
        - `price`: Double
        - `category`: Category 
    - `Category`: This class will represent the category entity.
      - > Based on the API documentations and details, the Category class will have the following attributes for now (APIs taken from [FakeStoreAPI](https://fakestoreapi.com/docs)):
        - `id`: Long
        - `name`: String
        - `description`: String

7. In the `controller` package, create the following classes:
    - `ProductController`: This class will contain the REST endpoints for the product entity. 
      - > Annotations to be used:
        - `@RestController`: This annotation is used to create RESTful web services using Spring MVC.
          - It tells Spring that this class will serve as a controller for handling HTTP requests and will be the first class to be scanned when the application starts. 
        - `@RequestMapping`: This annotation is used to map web requests to specific handler classes and/or handler methods.
      - > The ProductController class will have the following methods:
        - `getAllProducts()`: This method will return a list of all products.
        - `getProductById(Long id)`: This method will return a product by its id.
        - `addProduct(Product product)`: This method will add a new product.
        - `updateProduct(Long id, Product product)`: This method will update an existing product.
        - `deleteProduct(Long id)`: This method will delete a product by its id.
    - `CategoryController`: This class will contain the REST endpoints for the category entity.
      - > The CategoryController class will have the following methods:
        - `getAllCategories()`: This method will return a list of all categories.
        - `getCategoryById(Long id)`: This method will return a category by its id.
        - `addCategory(Category category)`: This method will add a new category.
        - `updateCategory(Long id, Category category)`: This method will update an existing category.
        - `deleteCategory(Long id)`: This method will delete a category by its id.