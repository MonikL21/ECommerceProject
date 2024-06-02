package com.example.demo.service;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
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
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        Category cat=categoryRepository.findByTitle(product.getCategory().getTitle());
        if(cat==null)
        {
            Category newCat=new Category();
            newCat.setTitle(product.getCategory().getTitle());
            Category newRow=categoryRepository.save(newCat);
            product.setCategory(newRow);
        }
        else {
            product.setCategory(cat);
        }
        Product savedProduct=productRepository.save(product);
        return savedProduct;
    }
}
