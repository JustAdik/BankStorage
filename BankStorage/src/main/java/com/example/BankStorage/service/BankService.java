package com.example.BankStorage.service;

import com.example.BankStorage.model.BankUser;
import com.example.BankStorage.repository.BankRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BankService implements UserDetailsService{

    private final BankRepository bankRepository;
    private final PasswordEncoder passwordEncoder;

    public BankService(BankRepository bankRepository, PasswordEncoder passwordEncoder) {
        this.bankRepository = bankRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void admin() {
        if(bankRepository.count() == 0) {
            BankUser admin = new BankUser("Adilet Yeraliev", "ADMIN", 0, passwordEncoder.encode("superadik2005"),"KZ1234567");
            bankRepository.save(admin);
        }
    }

    public BankUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return null;
        }
        String username = auth.getName();
        return bankRepository.findByUsername(username).orElse(null);
    }

    //пополнение
    public void topUp(int amount) {
        BankUser bankUser = getCurrentUser();
        bankUser.setBalance(bankUser.getBalance() + amount);
        bankRepository.save(bankUser);
    }

    public UserDetails loadUserByUsername(String username) {
        BankUser bankUser = bankRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Такого пользователя не существует"));

        return User.builder()
                .username(bankUser.getUsername())
                .password(bankUser.getPassword())
                .roles(bankUser.getRole().replace("ROLE_", ""))
                .build();
    }

    //закидывать деньги
    @Transactional
    public void transfer(String recieverAccount, int amount) {
        BankUser sender = getCurrentUser();
        BankUser receiver = bankRepository.findByIdenticalNumber(recieverAccount).orElseThrow();

        if(sender.getBalance() < amount) {
            throw new RuntimeException("Недастаточно средств");
        }

        if(sender.getIdenticalNumber().equals(recieverAccount)) {
            throw new RuntimeException("Вы не можете переводить самому себе");
        }
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        bankRepository.save(sender);
        bankRepository.save(receiver);
    }

}