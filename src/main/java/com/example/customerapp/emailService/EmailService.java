package com.example.customerapp.emailService;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendEmail(){
        System.out.println("email sent to client");
    }
    
}
