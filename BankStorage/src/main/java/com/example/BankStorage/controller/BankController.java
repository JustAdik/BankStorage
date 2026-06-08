package com.example.BankStorage.controller;
import com.example.BankStorage.model.BankUser;
import com.example.BankStorage.model.Transaction;
import com.example.BankStorage.repository.BankRepository;
import com.example.BankStorage.repository.TransactionRepository;
import com.example.BankStorage.service.BankService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankController {

    private final BankService bankService;
    private final BankRepository bankRepository;
    private final TransactionRepository transactionRepository;

    public BankController(BankService bankService, BankRepository bankRepository, TransactionRepository transactionRepository) {
        this.bankRepository = bankRepository;
        this.bankService = bankService;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/bank")
    public String bank(Model model) {
        model.addAttribute("title", "Главная");
        model.addAttribute("user", bankService.getCurrentUser());
        return "bank";
    }

    @PostMapping("/topup")
    public String topup(@RequestParam int amount, Model model) {
        bankService.topUp(amount);
        model.addAttribute("message", "Ваш баланс пополнен на " + "тенге");
        model.addAttribute("user", bankService.getCurrentUser());
        return "bank";
    }

    @GetMapping("/transfer")
    public String transferPage() {
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String receiverAccount, @RequestParam int amount) {
        bankService.transfer(receiverAccount, amount);
        return "redirect:/bank";
    }

    @GetMapping("/history")
    public String history(Model model) {
        BankUser bankUser = bankService.getCurrentUser();
        model.addAttribute("transactions", transactionRepository.findBySenderAccountOrReceiverAccount(bankUser.getIdenticalNumber(), bankUser.getIdenticalNumber()));
        return "history";
    }
}
