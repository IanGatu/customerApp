
package com.example.customerapp.customerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
     if (id <= 0) {
        throw new IllegalArgumentException("Invalid ID");
    }
    return repository.findById(id)
            .orElseThrow(() ->
                new CustomerNotFoundException(
                    "Customer not found, try another id"
                )
            );
    }
    @Transactional
   public Customer updateCustomer(int id, String newName) {
    Customer customer = repository.findById(id)
            .orElseThrow(() ->
                new CustomerNotFoundException(
                    "Customer not found"
                ));

    customer.setName(newName);

    return customer;
   }

   @Transactional 
   public Customer createCustomer( Customer customer){
     return repository.save(customer);
   }

   @Transactional 
   public void deleteCustomer( int id){
     Customer customer = repository.findById(id)
            .orElseThrow(() ->
                new CustomerNotFoundException(
                    "Customer not found"
                ));
    
       repository.deleteById(id);
   }
}