package com.example.customerapp.customernotfoundexception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException( String message){
        super(message);
    }
    
}
