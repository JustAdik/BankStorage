package com.example.BankStorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankController {
    @GetMapping("/")
    public String bank(Model model) {
        model.addAttribute("title", "Главная");
        return "bank";
    }
}
