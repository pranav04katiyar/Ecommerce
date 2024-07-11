package com.project.ecommerce_productservice.repositories;

import com.project.ecommerce_productservice.models.Category;
import com.project.ecommerce_productservice.models.Product;
import com.project.ecommerce_productservice.services.SelfProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findTop5ByTitleContaining(String title);

    long deleteByTitleIgnoreCase(String title);

    List<Product> findByTitleAndAndDescription(String title, String description);

    List<Product> findByPriceBetween(double startRange, double endRange);

    List<Product> findByCategory(Category category);

    Product findByIdAndCategoryOrderByTitle(Long id, Category category);

    List<Product> findByCategory_Id(Long id);

    Optional<Product> findById(Long id);

    Product save(Product product);

    Product save(SelfProductService product);
}
