package com.example.demo.service;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Product;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
//        List<Product> getAllProducts() throws ProductNotFoundException;
    Page<Product> getAllProducts(int pageSize, int pageNumber,String fieldName);
            Product createProduct(Product product) throws ProductNotFoundException;
           void deleteProduct(Long productId) throws ProductNotFoundException;
            Product updateProduct(Product product) throws ProductNotFoundException;
}
