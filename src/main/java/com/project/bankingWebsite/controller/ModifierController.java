package com.project.bankingWebsite.controller;

import com.project.bankingWebsite.model.Account;
import com.project.bankingWebsite.model.Client;
import com.project.bankingWebsite.model.User;
import com.project.bankingWebsite.services.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        }
        catch (IllegalStateException e)
        {
            model.addAttribute("error",e.getMessage());
        }
        return "registration";
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
        Account accountTo;
        try
        {
             accountTo = accountService.loadAccount(transactionRequest.getReceiverType(),
                    transactionRequest.getReceiver());
        }
        catch (IllegalArgumentException e)
        {
            model.addAttribute("error",e.getMessage());
            return "makeTransaction";
        }
        User user = (User) userService.loadUserByUsername(userDetails.getUsername());
        Client client = clientService.loadClient(user);
        Account accountFrom = accountService.loadAccount(client);
        accountFrom.setBalance(accountFrom.getBalance() - transactionRequest.getAmount());
        accountTo.setBalance(accountTo.getBalance() + transactionRequest.getAmount());
        transactionService.makeTransaction(accountFrom, accountTo,
                transactionRequest.getAmount());
        accountService.updateAccount(accountFrom);
        accountService.updateAccount(accountTo);
        return "makeTransaction";
    }

    @PostMapping("/admin/viewClients")
    public String modifyClient(@ModelAttribute("client") Client client, @ModelAttribute("action") String action,
                               Model model)
    {
        User user;
        try
        {
            user = (User) userService.loadUserByUsername(client.getUser().getUsername());
        }
        catch (UsernameNotFoundException e)
        {
            model.addAttribute("error","Utilizatorul cu numele" +
                    client.getUser().getUsername() + "nu a fost găsit!");
            model.addAttribute("clients", clientService.loadAllClients());
            return "viewClients";
        }
        if(action.equals("modify")) {
            Client editClient = clientService.loadClient(user);
            if (!client.getLastName().equals(""))
                editClient.setLastName(client.getLastName());
            if (!client.getFirstName().equals(""))
                editClient.setFirstName(client.getFirstName());
            if (!client.getCNP().equals(""))
                editClient.setCNP(client.getCNP());
            if (!client.getAddress().equals(""))
                editClient.setAddress(client.getAddress());
            if (!client.getEmail().equals(""))
                editClient.setEmail(client.getEmail());
            if (!client.getPhoneNumber().equals(""))
                editClient.setPhoneNumber(client.getPhoneNumber());
            clientService.updateClient(editClient);
        }
        else
        {
            Client toBeDeletedClient = clientService.loadClient(user);
            Account toBeDeletedAccount = accountService.loadAccount(toBeDeletedClient);
            transactionService.deleteTransactionByAccountFrom(toBeDeletedAccount);
            transactionService.deleteTransactionByAccountTo(toBeDeletedAccount);
            accountService.deleteAccount(toBeDeletedAccount);
            clientService.deleteClient(toBeDeletedClient);
            userService.deleteUser(user.getUsername());
        }
        model.addAttribute("clients", clientService.loadAllClients());
        return "viewClients";
    }

}
