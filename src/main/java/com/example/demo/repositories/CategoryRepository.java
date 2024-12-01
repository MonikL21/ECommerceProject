package com.example.demo.repositories;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);
    List<Category> findAll();
    Category findByTitle(String title);
    Optional<Category> findById(Long id);
    void deleteById(Long categoryId);

}
