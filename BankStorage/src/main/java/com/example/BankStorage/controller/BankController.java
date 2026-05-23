package com.example.BankStorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
