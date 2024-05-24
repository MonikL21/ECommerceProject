package com.example.demo.service;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId);
        List<Product> getAllProducts();
            Product createProduct(Product product);
}
