package com.example.BankStorage.service;

import com.example.BankStorage.model.BankUser;
import com.example.BankStorage.model.Message;
import com.example.BankStorage.model.Transaction;
import com.example.BankStorage.repository.BankRepository;
import com.example.BankStorage.repository.MessageRepository;
import com.example.BankStorage.repository.TransactionRepository;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankService implements UserDetailsService{

    private final BankRepository bankRepository;
    private final PasswordEncoder passwordEncoder;
    private final TransactionRepository transactionRepository;
    private final MessageRepository messageRepository;

    public BankService(BankRepository bankRepository,
                       PasswordEncoder passwordEncoder,
                       TransactionRepository transactionRepository,
                       MessageRepository messageRepository) {
        this.bankRepository = bankRepository;
        this.passwordEncoder = passwordEncoder;
        this.transactionRepository = transactionRepository;
        this.messageRepository = messageRepository;
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

    public List<Message> getMessage(String sender, String receiver) {
        return messageRepository.findAll();
    }

    //пополнение
    public void topup(int amount) {
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
    public void transfer(String receiverAccount, int amount) {
        BankUser sender = getCurrentUser();
        BankUser receiver = bankRepository.findByIdenticalNumber(receiverAccount).orElseThrow();

        if(sender.getBalance() < amount) {
            throw new RuntimeException("Недостаточно средств");
        }

        if(sender.getIdenticalNumber().equals(receiver.getIdenticalNumber())) {
            throw new RuntimeException("Вы не можете отправлять средства самому себе");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        //история
        Transaction transaction = new Transaction(sender.getIdenticalNumber(), receiverAccount, amount, LocalDateTime.now());

        bankRepository.save(sender);
        bankRepository.save(receiver);
        transactionRepository.save(transaction);
    }

    //поиск
    public BankUser search(String receiverAccount) {
        BankUser sender = getCurrentUser();

        if(sender.getIdenticalNumber().equals(receiverAccount)) {
            throw new RuntimeException("Вы не можете ввести себя в поиск");
        }

        return bankRepository.findByIdenticalNumber(receiverAccount)
                .orElseThrow(() -> new RuntimeException("Ползователь не найден"));
    }

    //чат
    public void message(String sender, String receiver, String text) {
        Message message = new Message();
        message.setSenderMessage(sender);
        message.setReceiverMessage(receiver);
        message.setText(text);
        message.setDate(LocalDateTime.now());

        messageRepository.save(message);
    }



}