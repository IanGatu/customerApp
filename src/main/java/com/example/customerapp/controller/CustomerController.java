package com.example.customerapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerapp.customer.Customer;
import com.example.customerapp.customerService.CustomerService;

import jakarta.validation.Valid;

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
    @PutMapping("/customer/{id}")
    public Customer updateCustomer(@PathVariable int id,  @RequestParam String name) {
        return service.updateCustomer(id, name);
        
    }

    @PostMapping("/customer")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return service.createCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")
public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
    service.deleteCustomer(id);
    return ResponseEntity.noContent().build();
}

}

