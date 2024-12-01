package com.example.demo.service;

import com.example.demo.dto.FakeStoreProductDto;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;




@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;
    private final RedisTemplate redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

//    public FakeStoreProductService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }


    @Override
    public Product getSingleProduct(Long productId)
    {
        Product productFromRedis=(Product)redisTemplate.opsForHash().get("PRODUCTS","PRODUCTS_"+productId);
        if(productFromRedis!=null)
        {
            return productFromRedis;
        }

        FakeStoreProductDto fakeStoreProductDto=restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );
        System.out.printf(fakeStoreProductDto.toString());
//        if(fakeStoreProductDto == null)
//        {
//            throw new ProductNotFoundException("Product not found"+"with id"+productId);
//        }
//        System.out.printf(fakeStoreProductDto.toString());
        redisTemplate.opsForHash().put("PRODUCTS","PRODUCTS_"+productId,fakeStoreProductDto.toProduct());
        return fakeStoreProductDto.toProduct();
    }


    //implementation of getallproducts
    @Override
    public List<Product> getAllProducts()
    {
      List<Product> products =new ArrayList<>();
      FakeStoreProductDto[] res=restTemplate.getForObject(
              "https://fakestoreapi.com/products/",FakeStoreProductDto[].class
      );
      for(FakeStoreProductDto fs:res)
      {
          products.add(fs.toProduct());
      }
      return products;
    }

//    public Page<Product> getAllProducts(int pageSize,int pageNumber,String fieldName)
//    {
////        List<Product> products =new ArrayList();
////        FakeStoreProductDto[] res=restTemplate.getForObject(
////                "https://fakestoreapi.com/products/",FakeStoreProductDto[].class
////        );
////        for(FakeStoreProductDto fs:res)
////        {
////            products.add(fs.toProduct());
////        }
////        return products;
//        return null;
//    }


    //implementation of createProduct
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

    @Override
    public void deleteProduct(Long productId)
    {

        restTemplate.delete("https://fakestoreapi.com/products/" +productId);
    };


//    @Override
//    public Product updateProduct(Long id,Product product)
//    {
//        FakeStoreProductDto fs = new FakeStoreProductDto();
//        fs.setId(product.getId());
//        fs.setTitle(product.getTitle());
//        fs.setDescription(product.getDescription());
//        fs.setImage(product.getImageUrl());
//        fs.setCategory(product.getCategory().getTitle());
//        fs.setPrice(product.getPrice());
//       restTemplate.put("https://fakestoreapi.com/products" +id, fs);
//       return product;
//
//    }
    @Override
    public Product updateProduct(Product product) {
        FakeStoreProductDto fs = new FakeStoreProductDto();
        fs.setId(product.getId());
        fs.setTitle(product.getTitle());
        fs.setDescription(product.getDescription());
        fs.setImage(product.getImageUrl());
        fs.setCategory(product.getCategory().getTitle());
        fs.setPrice(product.getPrice());

        restTemplate.put("https://fakestoreapi.com/products/" + product.getId(), fs);

        return getSingleProduct(product.getId());
    }

    @Override
    public List<Product> getAllProductsByCategoryId(Long categoryId) throws CategoryNotFoundException {
        return List.of();
    }

    @Override
    public void deleteAllProductsByCategoryId(Long categoryId) throws CategoryNotFoundException {

    }

    @Override
    public List<Category> getAllCategory() throws CategoryNotFoundException {
        return List.of();
    }


}
