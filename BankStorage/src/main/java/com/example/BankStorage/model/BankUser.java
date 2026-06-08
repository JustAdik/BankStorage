package com.example.BankStorage.model;

import jakarta.persistence.*;

@Entity
public class BankUser {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String role;
    private int balance;
    private String password;
    private int age;
    @Column(unique = true)
    private String identicalNumber;


    public BankUser() {
    }

    public BankUser(String name, String role, int balance, String password, String identicalNumber) {
        this.username = name;
        this.role = role;
        this.balance = balance;
        this.password = password;
        this.identicalNumber = identicalNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdenticalNumber() {
        return identicalNumber;
    }

    public void setIdenticalNumber(String identicalNumber) {
        this.identicalNumber = identicalNumber;
    }
}
