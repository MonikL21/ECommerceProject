package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public class DBStoreProductService implements ProductService{
    @Override
    public Product getSingleProduct(Long productId) {
        return null;
    }

    @Override
//    public List<Product> getAllProducts() {
//        return List.of();
//    }
    public Page<Product> getAllProducts(int pageSize, int pageNumber,String fieldName)
    {
        return null;
    };

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }
}
