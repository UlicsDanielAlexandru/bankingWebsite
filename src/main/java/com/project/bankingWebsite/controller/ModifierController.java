package com.project.bankingWebsite.controller;

import com.project.bankingWebsite.model.Account;
import com.project.bankingWebsite.model.Client;
import com.project.bankingWebsite.model.User;
import com.project.bankingWebsite.services.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class ModifierController {

    private RegistrationService registrationService;
    private UserService userService;
    private ClientService clientService;
    private AccountService accountService;
    private TransactionService transactionService;

    @GetMapping("/admin/registration")
    public String register(Model model)
    {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        model.addAttribute("registrationRequest", registrationRequest);
        return "registration";
    }

    @PostMapping("/admin/registration")
    public String saveUser(@ModelAttribute("registrationRequest") RegistrationRequest registrationRequest,
                           Model model)
    {
        try
        {
            registrationService.register(registrationRequest);
            return "registration";
        }
        catch (IllegalStateException e)
        {
            model.addAttribute("error",e.getMessage());
            return "registration";
        }
    }

    @GetMapping("/client/makeTransaction")
    public String initTransaction(Model model)
    {
        model.addAttribute("transactionRequest", new TransactionRequest());
        return "makeTransaction";
    }

    @PostMapping("/client/makeTransaction")
    public String makeTransaction(@AuthenticationPrincipal UserDetails userDetails,
                                  @ModelAttribute("transactionRequest") TransactionRequest transactionRequest,
                                  Model model)
    {
        User user = (User) userService.loadUserByUsername(userDetails.getUsername());
        Client client = clientService.loadClient(user);
        Account accountFrom = accountService.loadAccount(client);
        Account accountTo = accountService.loadAccount(transactionRequest.getReceiver());
        accountFrom.setBalance(accountFrom.getBalance() - transactionRequest.getAmount());
        accountTo.setBalance(accountTo.getBalance() + transactionRequest.getAmount());
        transactionService.makeTransaction(accountFrom, accountTo,
                transactionRequest.getAmount() * -1);
        transactionService.makeTransaction(accountTo, accountFrom,
                transactionRequest.getAmount());
        accountService.updateAccount(accountFrom);
        accountService.updateAccount(accountTo);
        return "makeTransaction";
    }

}
