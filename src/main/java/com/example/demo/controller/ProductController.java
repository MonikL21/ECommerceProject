package com.example.demo.controller;

import com.example.demo.dto.ErrorDto;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {

private final ProductService productService;

public ProductController(@Qualifier("selfProductService") ProductService productService)
{
    this.productService=productService;
}

//get product by id
@GetMapping("/products/{id}")
public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId) throws ProductNotFoundException
{
Product currentProduct=productService.getSingleProduct(productId);
//return currentProduct;
ResponseEntity<Product> res =new ResponseEntity<>(currentProduct, HttpStatus.OK);
return res;
}

//get all products
@GetMapping("/products")
//public  ResponseEntity<List<Product>> getAllProducts() throws ProductNotFoundException
//{
//   List<Product> products= productService.getAllProducts();
//    ResponseEntity<List<Product>> res =new ResponseEntity<>(products, HttpStatus.OK);
//    return res;
//}
public Page<Product> getAllProducts(@RequestParam("pageSize") int pageSize,
                                    @RequestParam("pageNumber") int pageNumber,
                                    @RequestParam("sortBy") String fieldName)
{
    return productService.getAllProducts(pageSize,pageNumber,fieldName);
}

//create product
@PostMapping("/products")
public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ProductNotFoundException
{
    Product postRequestResponse = productService.createProduct(product);
    return new ResponseEntity<>(postRequestResponse, HttpStatus.CREATED);
}

//Update product using PUT
@PutMapping("/products/{id}")
public  ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) throws ProductNotFoundException
{
    Product updatedProduct=productService.updateProduct(product);
    ResponseEntity<Product>res=new ResponseEntity(updatedProduct,HttpStatus.OK);
    return res;
}

//Delete product by id
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ProductNotFoundException
    {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> checkHealthOfTheService() {
        return new ResponseEntity<>("Backend application is running perfectly fine", HttpStatus.OK);
    }

//can be added here but created separate package
//controller advice

}
