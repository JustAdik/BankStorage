package com.example.BankStorage.service;

import com.example.BankStorage.model.BankUser;
import com.example.BankStorage.repository.BankRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BankService implements UserDetailsService{

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public UserDetails loadByUsername(String username) {
        BankUser bankUser = bankRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Такого пользователя не существует"));

        return User.builder()
                .username(bankUser.getName())
                .password(bankUser.getPassword())
                .roles(bankUser.getRole().replace("ROLE_", ""))
                .build();
    }
}
