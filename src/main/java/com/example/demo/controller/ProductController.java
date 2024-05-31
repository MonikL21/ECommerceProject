package com.example.demo.controller;

import com.example.demo.dto.ErrorDto;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId) throws ProductNotFoundException
{
Product currentProduct=productService.getSingleProduct(productId);
//return currentProduct;
ResponseEntity<Product> res =new ResponseEntity<>(currentProduct, HttpStatus.OK);
return res;
}
@GetMapping("/products")
public  ResponseEntity<List<Product>> getAllProducts()
{
   List<Product> products= productService.getAllProducts();
    ResponseEntity<List<Product>> res =new ResponseEntity<>(products, HttpStatus.OK);
    return res;
}
//controller advice
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e)
    {
        ErrorDto errorDto=new ErrorDto();
        errorDto.setMessage(e.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}