package com.example.customerapp.globalexceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.customerapp.ApiValidationError.ApiValidationError;
import com.example.customerapp.apierror.ApiError;
import com.example.customerapp.customernotfoundexception.CustomerNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)

        public ResponseEntity<ApiError> handleCustomerNotFound(CustomerNotFoundException ex,HttpServletRequest request){

             ApiError error = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiValidationError> handleValidationErrors(
        MethodArgumentNotValidException ex) {

    List<String> messages = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .distinct()
            .toList();

    ApiValidationError response = new ApiValidationError(
            HttpStatus.BAD_REQUEST.value(),
            "Validation failed",
            messages
    );

    return ResponseEntity.badRequest().body(response);
}
    
}
