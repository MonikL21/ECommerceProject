package com.example.demo.controller;

import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        int orderId = orderService.createOrder(order);
        return ResponseEntity.ok("Order created with ID: " + orderId);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order); // Wrap the `Order` object in `ResponseEntity`
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no orders
        }
        return ResponseEntity.ok(orders); // 200 OK with orders
    }




}
