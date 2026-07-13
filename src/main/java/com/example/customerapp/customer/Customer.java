package com.example.customerapp.customer;

public class Customer {
    public int id;
    public String name;
    public String accountType;

    public Customer(int id, String name, String accountType){
        this.id = id;
        this.name = name;
        this.accountType = accountType;
    }
}