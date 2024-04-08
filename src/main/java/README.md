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

6. In the `model` package, create the following classes:
    - `Product`: This class will represent the product entity.
      - > The Product class will have the following attributes:
        - `id`: Long (Ultimately we use UUID but for now, Long over int because it is a wrapper class, it can be null, avoiding null pointer exceptions. Also, due to its size, it can store a large number of values (2^64))
        - `name`: String
        - `description`: String
        - `price`: Double
        - `category`: Category 
    - `Category`: This class will represent the category entity.
      - > The Category class will have the following attributes:
        - `id`: Long
        - `name`: String
        - `description`: String