package com.project.bankingWebsite.controller;

import com.project.bankingWebsite.model.Client;
import com.project.bankingWebsite.model.User;
import com.project.bankingWebsite.services.AccountService;
import com.project.bankingWebsite.services.ClientService;
import com.project.bankingWebsite.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class VisualizerController {

    private ClientService clientService;
    private UserService userService;
    private AccountService accountService;

    @GetMapping("/client/accountInfo")
    public String visualizeAccountInfo(@AuthenticationPrincipal UserDetails userDetails, Model model)
    {
        User user = (User) userService.loadUserByUsername(userDetails.getUsername());
        Client client = clientService.loadClient(user);
        model.addAttribute("account", accountService.loadAccount(client));
        return "accountInfo";
    }
}
