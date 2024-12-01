package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @JsonIgnore
//    @OneToMany(mappedBy = "product")
//    private List<Customer> customers;

    //    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
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


}
