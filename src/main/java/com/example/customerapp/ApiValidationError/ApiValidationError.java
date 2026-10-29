package com.example.customerapp.ApiValidationError;

import java.util.List;


public record ApiValidationError (
    int status,
    String error,
    List<String> messages
){
   
}

