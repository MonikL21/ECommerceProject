package com.example.demo.service;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(SelfProductService.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(productId);
        if (p.isPresent()) {
            return p.get();
        }
        throw new ProductNotFoundException("Product not found");
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        return productRepository.findAll();

    }
//@Override
//    public Page<Product> getAllProducts(int pageSize, int pageNumber,String fieldName) {
//        Page<Product>allProducts=productRepository.findAll(PageRequest.of(pageNumber,pageSize, Sort.by(fieldName).ascending()));
//        return allProducts;
//    }

    @Override
    public Product createProduct(Product product) throws ProductNotFoundException
    {

//        let's say we are not passing category ID in our request body
        Product response = new Product();
        try {
            Category cat = categoryRepository.findByTitle(product.getCategory().getTitle());
            if (cat == null) {
                Category newCat = new Category();
                newCat.setTitle(product.getCategory().getTitle());
                Category newRow = categoryRepository.save(newCat);
                product.setCategory(newRow);

            } else {
                product.setCategory(cat);
            }
            response = productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProductNotFoundException("Product not found");
        }
        return response;

    }

    @Override
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(productId);
        if (p.isPresent()) {
            productRepository.deleteById(productId);
            System.out.println("Product deleted");
        } else {
            throw new ProductNotFoundException("Product not found");
        }
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(product.getId());
        if (p.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }

        Product existingInDbProd = p.get();
        if (product.getTitle() != null) {
            existingInDbProd.setTitle(product.getTitle());
        }
        if (product.getDescription() != null) {
            existingInDbProd.setDescription(product.getDescription());
        }
        if (product.getImageUrl() != null) {
            existingInDbProd.setImageUrl(product.getImageUrl());
        }
        existingInDbProd.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            Category cat = new Category();
            cat.setTitle(product.getCategory().getTitle());
            existingInDbProd.setCategory(cat);
        }
        return productRepository.save(existingInDbProd);

    }

    @Override
    public List<Product> getAllProductsByCategoryId(Long categoryId) throws CategoryNotFoundException {
        Optional<Category> cat = categoryRepository.findById(categoryId);
        if (cat.isPresent()) {
            return cat.get().getProducts();
        }
        throw new CategoryNotFoundException("Category not found");
    }

    @Override
    public void deleteAllProductsByCategoryId(Long categoryId) throws CategoryNotFoundException {
        Optional<Category> cat = categoryRepository.findById(categoryId);

        if (cat.isPresent()) {
            // Assuming a cascade delete is set up between Category and Product
            categoryRepository.deleteById(categoryId);
            System.out.println("Category and all associated products deleted");
        } else {
            throw new CategoryNotFoundException("Category not found");
        }
    }

    @Override
    public List<Category> getAllCategory() throws CategoryNotFoundException {
        return categoryRepository.findAll();
    }
//not working

}









