package com.example.customerapp.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.customerapp.customernotfoundexception.CustomerNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)

        public ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    
}
