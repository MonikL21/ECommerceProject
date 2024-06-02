package com.example.demo.repositories;

import com.example.demo.model.Product;
import com.example.demo.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    //insert into product values
    Product findByTitle(String title);
    //select * from product where title={}
    Product findByDescription(String description);

    // How to implement HQL
    @Query("select p from Product p where p.category.id = :categoryId" )
    List<Product> getProductsByCategoryId(@Param("categoryId") Long categoryId);

    // How to implement native queries

    @Query(value = "select * from product p where p.category_id = :categoryId", nativeQuery = true)
    List<Product> getProductsByCategoryIdWithNativeQueries(@Param("categoryId") Long categoryId);


    // HQL with projections
    // Allows you to fetch certain specific columns from the database
    // How to implement HQL
    @Query("select p.title as title, p.id as id from Product p where p.category.id = :categoryId" )
    List<ProductProjection> getProductsByCategoryIdProjection(@Param("categoryId")Long categoryId);
}
