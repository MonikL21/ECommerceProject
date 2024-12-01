package com.example.demo.service;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
        List<Product> getAllProducts() throws ProductNotFoundException;
//    Page<Product> getAllProducts(int pageSize, int pageNumber,String fieldName);
            Product createProduct(Product product) throws ProductNotFoundException;
           void deleteProduct(Long productId) throws ProductNotFoundException;
            Product updateProduct(Product product) throws ProductNotFoundException;

            List<Product> getAllProductsByCategoryId(Long categoryId) throws CategoryNotFoundException ;
            void deleteAllProductsByCategoryId(Long categoryId) throws CategoryNotFoundException ;
    List<Category> getAllCategory() throws CategoryNotFoundException;

}
