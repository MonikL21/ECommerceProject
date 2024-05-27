package com.example.demo.service;

import com.example.demo.dto.FakeStoreProductDto;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto=restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );
        System.out.printf(fakeStoreProductDto.toString());
        if(fakeStoreProductDto == null)
        {
            throw new ProductNotFoundException("Product not found"+"with id"+productId);
        }
        System.out.printf(fakeStoreProductDto.toString());
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
      FakeStoreProductDto fs=new FakeStoreProductDto();
      fs.setId(product.getId());
      fs.setTitle(product.getTitle());
      fs.setCategory(product.getCategory().getTitle());
      fs.setImage(product.getImageUrl());
      fs.setDescription(product.getDescription());
      fs.setPrice(product.getPrice());



      FakeStoreProductDto response=restTemplate.postForObject(
              "https://fakestoreapi.com/products",
              fs,
              FakeStoreProductDto.class
      );
      return response.toProduct();
    }
}
