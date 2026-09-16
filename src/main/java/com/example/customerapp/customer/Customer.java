package com.example.customerapp.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="customers")
public class Customer {

    @Id
    public int id;

    public String name;

    @Column(name="account_type")
    public String accountType;

    public Customer(){

    }

    public Customer(int id, String name, String accountType){
        this.id = id;
        this.name = name;
        this.accountType = accountType;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getAccountType(){
        return accountType;
    }

    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAccountType(String accountType){
        this.accountType=accountType;
    }
}