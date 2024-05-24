package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.FakeStoreProductService;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

private ProductService productService;

public ProductController(ProductService productService)
{
    this.productService=productService;
}

@PostMapping("/products")
public void createProduct()
{

}
@GetMapping("/products/{id}")
public void getProduct(@PathVariable("id") Long productId)
{

}
@GetMapping("/products")
public void getAllProducts()
{

}
}