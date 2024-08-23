package com.example.demo.controller;

import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    private CustomerController(CustomerService customerService)
    {
        this.customerService=customerService;
    }
    @GetMapping ("/customer/{customerId}")
       public Customer getSingleCustomer(@PathVariable Long customerId) throws CustomerNotFoundException
        {
            return customerService.getSingleCustomer(customerId);
        }

    @GetMapping ("/customer")
    public List<Customer> getAllCustomer() throws CustomerNotFoundException
    {
        return customerService.getAllCustomer();
    }
    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws CustomerNotFoundException
    {
        Customer customerRes=customerService.createCustomer(customer);
        return new ResponseEntity<>(customerRes, HttpStatus.CREATED);
    }

}
