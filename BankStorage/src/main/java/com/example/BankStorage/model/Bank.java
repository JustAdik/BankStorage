package com.example.BankStorage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bank {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)

    private String name;
    private String role;
    private int balance;
    private String password;
    private int age;

    public Bank(String name, String role, int balance, String password, int age) {
        this.name = name;
        this.role = role;
        this.balance = balance;
        this.password = password;
        this.age = age;
    }


}
