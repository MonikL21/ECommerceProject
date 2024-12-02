package com.example.demo.service;

import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerService {
   Customer getSingleCustomer(Long CustomerId) throws CustomerNotFoundException;
   List<Customer> getAllCustomer();
    Customer createCustomer(Customer customer) throws CustomerNotFoundException , ProductNotFoundException;
    void deleteCustomer(Long CustomerId) throws CustomerNotFoundException;
    Customer updateCustomer(Long CustomerId, Customer customer) throws CustomerNotFoundException;

}
