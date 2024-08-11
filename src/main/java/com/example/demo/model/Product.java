package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseModel {
    public String title;
    private String description;
    private double price;
    private String imageUrl;
@ManyToOne
@JoinColumn(name = "category_id")
    private Category category;
    private int weight;
    private int height;

    @Override
    public String toString() {
        return "Product [title=" + title +
                ", description=" + description +
                ", price=" + price +
                ", imageUrl=" + imageUrl +
                ",category=" + category +
                '}';
    }

//    public void setCategoryId(Long categoryId) {
//        if (this.category == null) {
//            this.category = new Category();
//        }
//        this.category.setId(categoryId);
//    }
}
