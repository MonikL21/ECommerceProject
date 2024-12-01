package com.example.demo.service;

import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("customerService")
public class SelfCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public SelfCustomerService(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
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
    public Customer createCustomer(Customer customer) throws CustomerNotFoundException, ProductNotFoundException {

        Customer c = new Customer();

        // Set the basic fields from the input customer object
        c.setName(customer.getName());
        c.setPassword(customer.getPassword());
        c.setPhonenumber(customer.getPhonenumber());

        // Fetch the Product entity using the product ID from the input customer object
        if (customer.getProduct() != null && customer.getProduct().getId() != null) {
            Long productId = customer.getProduct().getId();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

            // Set the fetched Product entity to the new Customer object
            c.setProduct(product);

        }

        // Save the new Customer to the repository
        customerRepository.save(c);

        return c;

    }

    @Override
    public void deleteCustomer(Long customerId) throws CustomerNotFoundException {
        Optional<Customer> c=customerRepository.findById(customerId);
        if(c.isPresent()) {
            customerRepository.deleteById(customerId);
        }
        else {
            throw new CustomerNotFoundException("Customer id not found");
        }
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer) throws CustomerNotFoundException {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + customerId + " not found"));

        // Update the existing customer's details with the new details
        existingCustomer.setName(customer.getName());
        existingCustomer.setPhonenumber(customer.getPhonenumber());
        existingCustomer.setPassword(customer.getPassword());

        // Update other fields as necessary

        // Save the updated customer back to the repository
        return customerRepository.save(existingCustomer);
    }
}
