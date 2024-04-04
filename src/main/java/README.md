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
   - > `model`: This package will contain the model classes.
       - Model classes are created based on the nouns in the problem statement, here the nouns are:
           - `Product` and
           - `Category`
   - > `controller`: This package will contain the controller classes.
       - REST suggests that controllers should be named after entities(the resource they are handling), so the controller classes will be:
         - `ProductController` and 
         - `CategoryController`
   - > `repository`: This package will contain the repository classes.
       - The repository classes will be:
         - `ProductRepository` and
         - `CategoryRepository`
   - > `service`: This package will contain the service classes.
     > - The service classes will be:
         - `ProductService` and
         - `CategoryService`