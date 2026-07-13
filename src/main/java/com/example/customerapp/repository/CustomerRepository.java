
package com.example.customerapp.repository;

import org.springframework.stereotype.Repository;

import com.example.customerapp.customer.Customer;

@Repository
public class CustomerRepository {
    
    public Customer findById(int id){
        

    if(id == 1){
        return new Customer(
            1,
            "Ian",
            "Savings"
        );
    }

    return null;
   }
}
