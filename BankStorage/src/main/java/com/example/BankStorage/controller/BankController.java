package com.example.BankStorage.controller;
import com.example.BankStorage.model.BankUser;
import com.example.BankStorage.model.Message;
import com.example.BankStorage.model.Transaction;
import com.example.BankStorage.repository.BankRepository;
import com.example.BankStorage.repository.TransactionRepository;
import com.example.BankStorage.service.BankService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @GetMapping("/topup")
    public String topup(@RequestParam int amount, Model model) {
        bankService.topup(amount);
        model.addAttribute("message", "Ваш баланс пополнен на" + "тенге");
        return "bank";
    }

    @GetMapping("/transfer")
    public String transfer() {
    return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam("receiverAccount") String receiverAccount, @RequestParam int amount) {
        bankService.transfer(receiverAccount, amount);
        return "redirect:/bank";
    }

    @GetMapping("/history")
    public String history(Model model) {
        BankUser bankUser = bankService.getCurrentUser();

        model.addAttribute("transactions", transactionRepository.findBySenderAccountOrReceiverAccount(
                bankUser.getIdenticalNumber(),
                bankUser.getIdenticalNumber()
        ));
        return "history";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }

    @PostMapping("/search")
    public String search(@RequestParam String receiverAccount, Model model) {
        BankUser receiver = bankService.search(receiverAccount);
        model.addAttribute("receiver", receiver);
        return "redirect:/chat/" + receiver.getUsername();
    }

    @GetMapping("/chat/{username}")
    public String messagePage(@PathVariable String username, Model model) {
        BankUser bankUser = bankService.getCurrentUser();
        BankUser receiver = bankRepository.findByUsername(username).orElseThrow();
        List<Message> messages = bankService.getMessage(bankUser.getUsername(), username);
        model.addAttribute("receiver", receiver);
        model.addAttribute("currentUser", bankUser);
        model.addAttribute("messages", messages);
        return "chat";
    }

    @PostMapping("/chat")
    public String message(@RequestParam String receiver,
                          @RequestParam String text) {
        BankUser currentUser = bankService.getCurrentUser();
        bankService.message(currentUser.getUsername(), receiver, text);
        return "redirect:/chat/" + receiver;
    }

}
