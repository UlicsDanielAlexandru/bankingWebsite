package com.project.bankingWebsite.controller;

import com.project.bankingWebsite.model.User;
import com.project.bankingWebsite.services.ClientService;
import com.project.bankingWebsite.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AuthenticationController {

    private ClientService clientService;
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model)
    {
        return "login";
    }

    @GetMapping("/resetPassword")
    public String resetPassword() {
        return "resetPassword";
    }

    @GetMapping("/accessDenied")
    public String getAccessDenied() {
        return "accessDenied";
    }

    @GetMapping("/admin")
    public String goAdmin(@AuthenticationPrincipal UserDetails userDetails, Model model)
    {
        model.addAttribute("username", userDetails.getUsername());
        return "admin";
    }

    @GetMapping("/client")
    public String goClient(@AuthenticationPrincipal UserDetails userDetails, Model model)
    {
        User user = (User) userService.loadUserByUsername(userDetails.getUsername());
        model.addAttribute("client", clientService.loadClient(user));
        return "client";
    }
}
