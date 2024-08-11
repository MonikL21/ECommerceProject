package com.example.demo.repositories;

import com.example.demo.model.Product;
import com.example.demo.projections.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);


    Optional<Product> findById(Product productId);
    //insert into product values
    Product findByTitle(String title);
    //select * from product where title={}
    Product findByDescription(String description);

    Page<Product> findAll(Pageable pageable);

    void deleteById(Long productId);

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
//Product save(Product product);
//
//// The correct findById method should return an Optional<Product>
//Optional<Product> findById(Long productId);
//
//// Custom query methods
//Product findByTitle(String title);
//
//Product findByDescription(String description);
//
//void deleteById(Long productId);
//
//// HQL query
//@Query("select p from Product p where p.category.id = :categoryId")
//List<Product> getProductsByCategoryId(@Param("categoryId") Long categoryId);
//
//// Native query
//@Query(value = "select * from product p where p.category_id = :categoryId", nativeQuery = true)
//List<Product> getProductsByCategoryIdWithNativeQueries(@Param("categoryId") Long categoryId);
//
//// HQL with projections
//@Query("select p.title as title, p.id as id from Product p where p.category.id = :categoryId")
//List<ProductProjection> getProductsByCategoryIdProjection(@Param("categoryId") Long categoryId);