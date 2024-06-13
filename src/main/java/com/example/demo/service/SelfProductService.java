package com.example.demo.service;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService
{
    private static final Logger log= LoggerFactory.getLogger(SelfProductService.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> p= productRepository.findById(productId);
        if(p.isPresent())
        {
            return p.get();
        }
        throw new ProductNotFoundException("Product not found");
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException
    {
        List<Product>allProducts = productRepository.findAll();
        return allProducts;
    }

    @Override
    public Product createProduct(Product product) throws ProductNotFoundException
    {

//        let's say we are not passing category ID in our request body
            Product response = new Product();
            try
            {
                Category cat = categoryRepository.findByTitle(product.getCategory().getTitle());
                if(cat == null)
                {
                    Category newCat=new Category();
                    newCat.setTitle(product.getCategory().getTitle());
                    Category newRow=categoryRepository.save(newCat);
                    product.setCategory(newRow);
                }
                else {
                    product.setCategory(cat);
                }
                response=productRepository.save(product);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new ProductNotFoundException("Product not found");
            }
            return response;

    }

    @Override
    public void deleteProduct(Long productId) throws ProductNotFoundException
    {
        Optional<Product> p=productRepository.findById(productId);
        if(p.isPresent()) {
            productRepository.deleteById(productId);
           System.out.println("Product deleted");
        }
        else {
            throw new ProductNotFoundException("Product not found");
        }
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException
    {
      Optional<Product> p=productRepository.findById(product.getId());
       if(p.isEmpty())
       {
           throw new ProductNotFoundException("Product not found");
       }

     Product existingInDbProd=p.get();
       if(product.getTitle() != null)
      {
           existingInDbProd.setTitle(product.getTitle());
       }
       if(product.getDescription() != null)
       {
           existingInDbProd.setDescription(product.getDescription());
       }
       if(product.getImageUrl() != null)
       {
           existingInDbProd.setImageUrl(product.getImageUrl());
       }
       existingInDbProd.setPrice(product.getPrice());
       if(product.getCategory() != null) {
           Category cat = new Category();
           cat.setTitle(product.getCategory().getTitle());
           existingInDbProd.setCategory(cat);
       }
       return productRepository.save(existingInDbProd);

    }
}





