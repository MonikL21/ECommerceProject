package com.example.demo.controller;

import com.example.demo.model.Product;
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
public Product getProduct(@PathVariable("id") Long productId)
{
Product currentProduct=productService.getSingleProduct(productId);
return currentProduct;
}
@GetMapping("/products")
public void getAllProducts()
{

}
}