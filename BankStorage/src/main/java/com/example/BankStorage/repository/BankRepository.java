package com.example.BankStorage.repository;

import com.example.BankStorage.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<BankUser, Long> {
    Optional<BankUser> findByUsername(String username);
}
