package com.example.demo;

import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

}
//import com.example.demo.model.Product;
//import com.example.demo.projections.ProductProjection;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//void contextLoads() {
//
//    @Test
//    void testingQueries() {
////        List<Product> products = productRepository.getProductsByCategoryId(1L);
////        System.out.println(products.get(0));
//
////        List<Product> products = productRepository.getProductsByCategoryIdWithNativeQueries(1L);
////        System.out.println(products.get(0));
//
//
//    }   List<ProductProjection> projections = productRepository.getProductsByCategoryIdProjection(1L);
//    System.out.println(projections.get(0).getId());

//void fetchCategoryLazy()
//{
//    Category category=categoryRepository.findById(1L).get();
//    System.out.println(category.getId());
//    System.out.println("We are done here");
//
//    List<Product> currentProducts =category.getProducts();
//    System.out.println(currentProducts.size());
//    System.out.println("Products fetched");
//
//}
