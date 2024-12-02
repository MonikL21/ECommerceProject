package com.example.demo.service;

import com.example.demo.model.Order;

import java.util.List;

public interface OrderService {
    int createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
}
