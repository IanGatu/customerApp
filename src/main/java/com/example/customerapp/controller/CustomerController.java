package com.example.customerapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.customerapp.customerService.CustomerService;
import com.example.customerapp.customer.Customer;

@RestController
public class CustomerController {
    private final CustomerService service;

    // Fixed constructor name to match class
    public CustomerController(CustomerService service){
        this.service = service;
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable int id){
        return service.getCustomer(id); // Added return
    }
}
