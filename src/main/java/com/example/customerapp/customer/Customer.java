package com.example.customerapp.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name="customers")
public class Customer {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotBlank(message = "name can't be blank")
    public String name;

    @NotBlank(message = "accountTYpe can't be blank")
    @Column(name="account_type")
    public String accountType;

    public Customer(){

    }

    public Customer(Integer id, String name, String accountType){
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

    public void setId(Integer id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAccountType(String accountType){
        this.accountType=accountType;
    }
}