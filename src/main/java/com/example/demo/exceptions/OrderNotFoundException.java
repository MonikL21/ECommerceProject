package com.example.demo.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message, Long id) {
        super(message + " ID: " + id);
    }
}
