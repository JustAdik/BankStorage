package com.example.BankStorage.security;

import com.example.BankStorage.repository.BankRepository;
import com.example.BankStorage.service.BankService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BankSecurity {

    private final BankService bankService;
    private final PasswordEncoder passwordEncoder;

    public BankSecurity(BankService bankService, PasswordEncoder passwordEncoder) {
        this.bankService = bankService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain
}
