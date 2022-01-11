package com.project.bankingWebsite.controller;

import com.project.bankingWebsite.model.Account;
import com.project.bankingWebsite.model.Client;
import com.project.bankingWebsite.model.Transaction;
import com.project.bankingWebsite.model.User;
import com.project.bankingWebsite.services.AccountService;
import com.project.bankingWebsite.services.ClientService;
import com.project.bankingWebsite.services.TransactionService;
import com.project.bankingWebsite.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class VisualizerController {

    private ClientService clientService;
    private UserService userService;
    private AccountService accountService;
    private TransactionService transactionService;

    @GetMapping("/client/accountInfo")
    public String visualizeAccountInfo(@AuthenticationPrincipal UserDetails userDetails, Model model)
    {
        User user = (User) userService.loadUserByUsername(userDetails.getUsername());
        Client client = clientService.loadClient(user);
        model.addAttribute("account", accountService.loadAccount(client));
        return "accountInfo";
    }

    @GetMapping("/client/viewTransactions")
    public String visualizeTransactions(@AuthenticationPrincipal UserDetails userDetails, Model model)
    {
        User user = (User) userService.loadUserByUsername(userDetails.getUsername());
        Client client = clientService.loadClient(user);
        model.addAttribute("clientId", client.getId());
        Account account = accountService.loadAccount(client);
        List<Transaction> transactions = transactionService.loadTransactions(account);
        if(transactions.isEmpty()) {
            model.addAttribute("message", "Nu există tranzacții pentru acest cont");
        }
        else
        {
            model.addAttribute("transactions", transactions);
        }
        return "viewTransactions";
    }

    @GetMapping("/admin/viewAllTransactions")
    public String visualizeAllTransactions(Model model)
    {
        model.addAttribute("transactions", transactionService.loadAllTransactions());
        return "viewAllTransactions";
    }

    @GetMapping("/client/clientInfo")
    public String visualizeClientInfo(@AuthenticationPrincipal UserDetails userDetails, Model model)
    {
        User user = (User) userService.loadUserByUsername(userDetails.getUsername());
        Client client = clientService.loadClient(user);
        model.addAttribute("client", client);
        return "clientInfo";
    }

    @GetMapping("/admin/viewClients")
    public String visualizeClients(Model model)
    {
        model.addAttribute("clients", clientService.loadAllClients());
        model.addAttribute("client", new Client());
        return "viewClients";
    }
}
