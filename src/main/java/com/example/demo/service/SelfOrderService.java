package com.example.demo.service;

import com.example.demo.exceptions.OrderNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("orderService")

public class SelfOrderService implements OrderService {
    private final OrderRepository orderRepository;

    public SelfOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public int createOrder(Order order) {
        if (order.getCustomer() == null || order.getProduct() == null || order.getCategory() == null) {
            throw new IllegalArgumentException("Customer, Product, and Category are required fields.");
        }

        // Save the order to the database
        Order savedOrder = orderRepository.save(order);
        return Math.toIntExact(savedOrder.getId()); // Convert ID to int
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order=orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException("Order not found with this", id);

    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}

