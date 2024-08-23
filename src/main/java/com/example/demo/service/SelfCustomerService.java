package com.example.demo.service;

import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("customerService")
public class SelfCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;

    public SelfCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getSingleCustomer(Long customerId) throws CustomerNotFoundException {
        Optional<Customer> c=customerRepository.findById(customerId);
        if(c.isPresent())
            return c.get();
        else
           throw new CustomerNotFoundException("Customer id not found");
    }

    @Override
    public List<Customer> getAllCustomer() {

        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) throws CustomerNotFoundException {

       Customer c=new Customer();
       c.setId(customer.getId());
       c.setName(customer.getName());
       c.setPassword(customer.getPassword());
       c.setPhonenumber(customer.getPhonenumber());
       customerRepository.save(c);
       return c;

    }
}
