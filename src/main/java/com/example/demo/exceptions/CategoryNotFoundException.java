package com.example.demo.exceptions;

public class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(String message)
    {
        super(message);
    }
}
