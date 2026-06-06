package com.example.BankStorage.controller;

import com.example.BankStorage.model.BankUser;
import com.example.BankStorage.repository.BankRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private BankRepository bankRepository;
    private PasswordEncoder passwordEncoder;

    public AuthController(BankRepository bankRepository, PasswordEncoder passwordEncoder) {
        this.bankRepository = bankRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new BankUser());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute BankUser bankUser, Model model) {
        if(bankRepository.findByUsername(bankUser.getUsername()).isPresent()) {
            model.addAttribute("error", "Такой пользователь уже есть");
            return "register";
        }

        bankUser.setPassword(passwordEncoder.encode(bankUser.getPassword()));
        bankUser.setBalance(0);
        bankUser.setRole("USER");
        bankRepository.save(bankUser);
        return "redirect:/login";
    }
}


