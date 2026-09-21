
package com.example.customerapp.customerService;

import org.springframework.stereotype.Service;

import com.example.customerapp.customer.Customer;
import com.example.customerapp.customernotfoundexception.CustomerNotFoundException;
import com.example.customerapp.emailService.EmailService;
import com.example.customerapp.repository.CustomerRepository;


@Service
public class CustomerService {
    private final CustomerRepository repository;
    //private final EmailService emailService;


    // Constructor injection so Spring can inject the repository and email
    public CustomerService(CustomerRepository repository,EmailService emailService ) {
        this.repository = repository;
        //this.emailService=emailService;
    }

    public Customer getCustomer(int id) {

    return repository.findById(id)
            .orElseThrow(() ->
                new CustomerNotFoundException(
                    "Customer not found, try another id"
                )
            );
}
}